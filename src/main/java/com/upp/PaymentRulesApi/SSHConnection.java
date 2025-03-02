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
import com.upp.base.BaseClass;
import com.upp.utils.Property;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SSHConnection extends BaseClass{
   public static String file="";
   public static String paymentProcessor="";
   public static String paymentProcessorpodname="";
   public static String host="";
   public static String namespace="";
   
    public static ArrayList<String> getPainFileDetails(String batchId,ArrayList<String> tagNames){
        ArrayList<String> Result = new ArrayList<String>();
        String privateKeyPath = System.getProperty("user.dir") + "//src//main//resources//av.pem";
        String username = "ubuntu";

        if(prop.getProperty("env").equalsIgnoreCase("qa")) {
			host="13.126.59.0";
			namespace="upp-qa";				
		}
        
        if(prop.getProperty("env").equalsIgnoreCase("sit")) {
			host="3.7.121.236";
			namespace="appveensit";				
		}
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
                List<String> output3 = new ArrayList<>();
                
                String podcommand="kubectl get pods -n "+namespace;
                
                Channel channel3 = session.openChannel("exec");
                ((ChannelExec) channel3).setCommand(podcommand);
                channel3.connect();
                // Read the command output
                InputStream in3 = channel3.getInputStream();
                BufferedReader reader3 = new BufferedReader(new InputStreamReader(in3));

                String line3;
                while ((line3 = reader3.readLine()) != null) {
                    output3.add(line3);
                }
                
                for (String line4 : output3) {
                	
                	if(line4.startsWith("paymentprocessor"))
                	{
                		 paymentProcessorpodname=line4;
                		 System.out.println("Paymnt processor pod name is "+paymentProcessorpodname);
                	}
                 
              }
                
                // code to extract podname
                String split[]=paymentProcessorpodname.split(" ");
                paymentProcessor=split[0];
                System.out.println("the extracted paymentpodname is"+paymentProcessor);
                
                String grepCommand="kubectl  -n "+namespace+" exec "+paymentProcessor+" -- ls /mnt/payments | grep "+batchId;
                
                // Execute  commands
                String[] commands = {grepCommand};
                for (String command : commands) {
                    Channel channel = session.openChannel("exec");
                    ((ChannelExec) channel).setCommand(command);
                    channel.connect();
                    // Read the command output
                    InputStream in = channel.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
 
                    int counter=0;
                    String line;
                    while ((line = reader.readLine()) != null) {
                    	counter++;
                        output.add(line);
                    }
                    int latest_file=counter-1;
                   file= output.get(latest_file);
                   
                   
                    // Close the channel
                    channel.disconnect();
                }

               
                
                System.out.println("The UPP Pain File name is:"+file);
    
                
                String catCommand="kubectl -n "+namespace+" exec "+paymentProcessor+" -- cat /mnt/payments/"+file;
                
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
