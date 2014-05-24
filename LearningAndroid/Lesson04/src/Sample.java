
public class Sample {
	//////////////////////////////////////////////////
	// while文とfor文
	//////////////////////////////////////////////////
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		int temp = 5; 		// 最初の１回動作（初期化）
		while(temp > 0){	// ループの前判定
			System.out.println("AAAAAA");
			temp--;			// ループの後に変数を更新（ー１）
			// temp = temp - 1;
		}

		for(int	i=5;	// 最初の１回動作（初期化）
				i>0;	// ループの前判定
				i--) { // ループの後に変数を更新（ー１）
			System.out.println("BBBBBB");
		}
	}

}
