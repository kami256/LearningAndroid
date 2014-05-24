import java.util.ArrayList;
import java.util.Collections;


public class Sample {
	//////////////////////////////////
	// 配列とコレクションクラス
	//////////////////////////////////
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		int	temp[] = new int[5];
		
		temp[0] = 11;
		temp[1] = 14;
		temp[2] = 15;
		temp[3] = 12;
		temp[4] = 10;
		for(int i=0;i<temp.length;i++){
			System.out.println(temp[i]);
		}
		
		ArrayList<Integer> seito = new ArrayList<Integer>();
		seito.add(300);
		seito.add(250);
		seito.add(400);
		seito.add(100);
		Collections.sort(seito);
		//seito.remove(2);
		//seito.clear();
		for(int i=0;i<seito.size();i++){
			System.out.println(seito.get(i));
		}

	}

}
