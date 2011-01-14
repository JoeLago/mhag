
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @program MHAG
 * @ Generator Class
 * @version 1.0
 * @author Tifa@mh3
 */
public class Generator {

	static void generator(Mhag aMhag, MhagData aMhagData) throws FileNotFoundException
	{
		Generator gen = new Generator();
		gen.mhag = aMhag;
		gen.mhagData= aMhagData;

		gen.setOptimizer();
		//gen.numerateTest();

	}

	private void setOptimizer() throws FileNotFoundException
	{
		Set aSet = new Set();
		aSet.setSetFromFile(mhag, mhag.getFileIn());

		boolean pass = aSet.checkSet(mhag, mhagData);
		if(!pass)
		{
			MhagUtil.logLine(mhag, "Incorrect set read from input file!");
			return;
		}

		aSet.calcSet(mhag, mhagData);
		aSet.save(mhag, mhagData, System.out);

		for (int i = 0; i < Skill.skillIDTot; i++)
		{
			Skill aSkill = mhagData.getSkill(i);
			System.out.printf("%3d: ",i);
			for (int j = 1; j <= 3; j++)
			{
				int id = aSkill.getJewelID(j);
				if(id == -1)continue;
				Jewel aJewel = mhagData.getJewel(id);
				System.out.printf("%3d %3d %+3d; ",j , id,
					aSkill.getJewelSkillPoint(j));
			}
			System.out.println();

		}
	}

	private void numerateTest()  // only for test
	{
		mhag.setLogOpt(2); //off log

		int[] dim = new int[5];
		int[] indices = new int[5];
		boolean[] inUse = new boolean[5];
		Set aSet = new Set();
		Arrays.fill(inUse,true);

		int num = 1;
		for (int i =0; i < 5; i++)
		{
			dim[i] = 10; //Armor.getArmorIDTot()[i];
			num *= dim[i];
		}

		int count = 0;
		for (int i = 0; i < num; i++)
		{
			indices = MhagUtil.getIndexArray(i, dim); //Armor.getArmorIDTot());
			aSet.init();
			aSet.setArmorID(indices);

			for (int j = 0; j < 5; j++)
				aSet.setInUse(j,inUse[j]);

			boolean pass = aSet.checkSet(mhag, mhagData);  //check set
			if(!pass)continue;
			aSet.calcSet(mhag, mhagData);   //calculate set

			count++;
		}

	}

	private Mhag mhag;  //local mhag data
	private MhagData mhagData;  //local mhagData data
}
