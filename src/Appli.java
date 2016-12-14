
import sac.SacADos;

public class Appli {

	public static void main(String[] args) {
		SacADos sac = new SacADos(args[0], Double.valueOf(args[1]));
		switch (args[2]) {
		case "gloutonne":
			sac.résoudre_gloutonne();
			break;
		case "prog. dynamique":
			sac.résoudre_progDynamique();
			break;
		case "pse":
			sac.résoudre_pse();
			break;
		default:
			break;
		}
		System.out.println(sac);
	}

}
