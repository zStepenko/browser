import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8080);
		while (true) {
			System.out.println("Waiting client");
			Socket socket = server.accept();
			System.out.println("Clent accepted");
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			byte[] array = new byte[100];
			in.read(array);

			out.write(("HTTP/1.0 200 Ok\n" + "Content-type: text/html"+
						 "\n\n" +"<H1>HELLO!</H1>").getBytes());
			socket.close();
		}
	}

	static byte[] getFile(String fileName) throws IOException {
		File file = new File(fileName);
		byte[] content = null;
		if (file.exists()) {
			content = new byte[(int) file.length()];
			FileInputStream fin = new FileInputStream(file);
			fin.read(content);

		}
		return content;
	}

}
