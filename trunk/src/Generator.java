/**
 * @program MHAG
 * @ Generator Class
 * @version 1.0
 * @author Tifa@mh3
 */
public class Generator {

	static void generator(Mhag aMhag, MhagData aMhagData)
	{
		Generator gen = new Generator();
		gen.mhag = aMhag;
		gen.mhagData= aMhagData;

		//gen.numerateTest();

	}

	private void numerateTest()
	{
		long num = 1;
		for (int i = 0; i < 5; i++)
			num *= Armor.getArmorIDTot(i);

		int[] indeces = new int[5];

		int[] test = new int[3];
		num = 1;
		for (int i =0; i < 3; i++)
		{
			test[i] = Armor.getArmorIDTot()[i];
			num *= test[i];
		}

		for (int i = 0; i < num; i++)
		{
			indeces = MhagUtil.getIndexArray(i, test); //Armor.getArmorIDTot());
		}


		System.out.println(num);

	}

	private Mhag mhag;  //local mhag data
	private MhagData mhagData;  //local mhagData data
}
