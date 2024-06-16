package ignore;

public class ICode {

	public static void main(String[] args) {
		int count = 0;
		int length = 7;
		for (int i = 0; i < length; i++) {
			
			for (int j = 0; j < length; j++) {
				if(i==j || j==length-1-i) {
					System.out.print("*");
				}
				else {
					System.out.print(" ");
				}
				
			}
			System.out.println();
			
			
			
		}

	
	}

}
