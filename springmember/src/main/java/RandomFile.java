import java.util.UUID;

public class RandomFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filename = "clock.jpg";
		String extension = filename.substring(filename.lastIndexOf("."), filename.length());
		System.out.println("extension:"+extension);
		
		UUID uuid = UUID.randomUUID();	// 문자 형태의 난수 출력
		System.out.println("uuid:"+uuid);
		
		String newfilename = uuid.toString() + extension;
		System.out.println("newfilename:"+newfilename);
		
	}

}
