
import sac.SacADos;

public class Appli {

	public static void main(String[] args) {
		SacADos sac = new SacADos(args[0], Double.valueOf(args[1]));
		switch (args[2]) {
		case "gloutonne":
			sac.r�soudre_gloutonne();
			break;
		case "prog. dynamique":
			sac.r�soudre_progDynamique();
			break;
		case "pse":
			sac.r�soudre_pse();
			break;
		default:
			break;
		}
		System.out.println(sac);
	}

}
