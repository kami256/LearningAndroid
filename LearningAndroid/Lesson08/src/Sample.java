
public class Sample {
	///////////////////////////////////////
	// Stringクラス
	///////////////////////////////////////
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String	aaa = "ab,cde,fgfff,ssss,dddd";
		int ttt = aaa.length();
		System.out.println(ttt);
		System.out.println(aaa.indexOf("cde"));
		String	abc[] = aaa.split(",");
		for(int i=0;i<abc.length;i++){
			System.out.println(abc[i]);
		}
	}

}
