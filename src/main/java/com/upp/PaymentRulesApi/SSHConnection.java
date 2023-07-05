package com.upp.PaymentRulesApi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.upp.utils.Property;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SSHConnection {
   public static String file="";
   public static  ArrayList<String> Result = new ArrayList<String>();
   
    public static ArrayList<String> getPainFileDetails(String batchId,ArrayList<String> tagNames){
    	
        String privateKeyPath = System.getProperty("user.dir") + "//src//main//resources//av.pem";
        String username = "ubuntu";
        String host=Property.getProperty("host");
        String namespace=Property.getProperty("namespace");
        String podName=Property.getProperty("podName");
        int port = 22;

        JSch jSch = new JSch();
        try {
            // Load the private key
            jSch.addIdentity(privateKeyPath);

            // Create a new SSH session
            Session session = jSch.getSession(username, host, port);

            // Disable strict host key checking
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // Establish the SSH connection
            session.connect();

            // Check if the connection is successful
            if (session.isConnected()) {
                System.out.println("Connected to the server.");

                // Create a list to store the command output
                List<String> output = new ArrayList<>();
                List<String> output1 = new ArrayList<>();
                String grepCommand="kubectl  -n "+namespace+" exec "+podName+" -- ls /mnt/payments | grep "+batchId;

                // Execute  commands
                String[] commands = {grepCommand};
                for (String command : commands) {
                    Channel channel = session.openChannel("exec");
                    ((ChannelExec) channel).setCommand(command);
                    channel.connect();
                    // Read the command output
                    InputStream in = channel.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        output.add(line);
                    }
                   file= output.get(0);
                   
                   
                    // Close the channel
                    channel.disconnect();
                }

               
                
                System.out.println("The UPP File name is:"+file);
    
                
                String catCommand="kubectl -n "+namespace+" exec "+podName+" -- cat /mnt/payments/"+file;
                
                Channel channel1 = session.openChannel("exec");
                ((ChannelExec) channel1).setCommand(catCommand);
                channel1.connect();
                // Read the command output
                InputStream in1 = channel1.getInputStream();
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(in1));

                String line1;
                while ((line1 = reader1.readLine()) != null) {
                    output1.add(line1);
                }
                
                for (String line2 : output1) {
                  System.out.println(line2);
              }
                
                ArrayList<String> htmlLines =(ArrayList<String>) output1 ; 
               
                
                for(String tag: tagNames)
                {
               String res= getTagContent(htmlLines,tag);
               Result.add(res);
                
                }
               
                
                // Close the SSH session
                session.disconnect();
                System.out.println("Disconnected from the server.");
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return Result;
    }
    
    public static String getTagContent(ArrayList<String> output1,String tag)
    {
    	ArrayList<String> htmlLines =(ArrayList<String>)output1;

        ArrayList<String> extractedText = new ArrayList<>();
        

        String pattern = "<" + tag + "[^>]*>(.*?)</" + tag + ">";
        Pattern regex = Pattern.compile(pattern);

        for (String line : htmlLines) {
            Matcher matcher = regex.matcher(line);
            while (matcher.find()) {
                String text = matcher.group(1); // Extract the text within the HTML tag
                extractedText.add(text);
            }
        }

        // Print or process the extracted text
        System.out.println("The extracted Data from tags are:");
        for (String text : extractedText) {
            System.out.println(text);
        }
        
        String result=extractedText.get(0);
        return result;
    }
}
