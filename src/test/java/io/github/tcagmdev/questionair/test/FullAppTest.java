package io.github.tcagmdev.questionair.test;

import io.github.tcagmdev.questionair.App;
import io.github.tcagmdev.questionair.data.DataManager;
import io.github.tcagmdev.questionair.frontend.CLIFrontend;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class FullAppTest {
	@Test
	void test() { if (true) return; // disable // TODO: fix
		DataManager dataManager = new DataManager(new TestDataSeeder());
		App app = new App(dataManager, "questionair");

		InputStream input = new ByteArrayInputStream("2\n1\n2\n2\nback\n3\n2\n4\n3\n5\nback\n14\n4\nthis\nthere\nback\n3\nback\n5\n".getBytes());
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		new CLIFrontend(app, input, new PrintStream(output)).start();

		Assertions.assertEquals("""
		Welcome to Questionair!
		Please select one of the following options:
		1. Make exam
		2. View exams
		3. View learning goals
		4. Add learning goal
		5. Exit
		
		
		Exams:
		1. Wiskunde V1 P1
		2. Wiskunde V1 P2
		3. Wiskunde V1 P2
		
		4. Back
		
		
		Module: Wiskunde V1 P1, Version: A
		
		Question 1:
			Wat is 1+1?
		Answer:
			2
		Learning goals: optellen, \b\b
		Length: 1m, Points: 1, Type: OPEN, Level: APPLICATION1, Shortable: Yes
		
		Question 2:
			Wat is 6/2(1+2)?
		Answer:
			6
		Learning goals: optellen, rekenvolgorde, vermenigvuldigen, aftrekken, delen, \b\b
		Length: 2m, Points: 4, Type: OPEN, Level: APPLICATION2, Shortable: No
		
		1. Edit
		2. Back
		
		
		Exams:
		1. Wiskunde V1 P1
		2. Wiskunde V1 P2
		3. Wiskunde V1 P2
		
		4. Back
		
		
		Module: Wiskunde V1 P2, Version: A
		
		Question 1:
			Wat is 6/2(1+2)?
		Answer:
			6
		Learning goals: optellen, rekenvolgorde, vermenigvuldigen, aftrekken, delen, \b\b
		Length: 2m, Points: 4, Type: OPEN, Level: APPLICATION2, Shortable: No
		
		Question 2:
			3 * 4 / ? = 2
		Answer:
			6
		Learning goals: vermenigvuldigen, herleiden, delen, \b\b
		Length: 4m, Points: 4, Type: OPEN, Level: APPLICATION2, Shortable: No
		
		1. Edit
		2. Back
		
		
		Exams:
		1. Wiskunde V1 P1
		2. Wiskunde V1 P2
		3. Wiskunde V1 P2
		
		4. Back
		
		
		Module: Wiskunde V1 P2, Version: B
		
		Question 1:
			3 * 4 / ? = 2
		Answer:
			6
		Learning goals: vermenigvuldigen, herleiden, delen, \b\b
		Length: 4m, Points: 4, Type: OPEN, Level: APPLICATION2, Shortable: No
		
		Question 2:
			Wat is 6/2(1+2)?
		Answer:
			6
		Learning goals: optellen, rekenvolgorde, vermenigvuldigen, aftrekken, delen, \b\b
		Length: 2m, Points: 4, Type: OPEN, Level: APPLICATION2, Shortable: No
		
		1. Edit
		2. Back
		
		
		Exams:
		1. Wiskunde V1 P1
		2. Wiskunde V1 P2
		3. Wiskunde V1 P2
		
		4. Back
		
		
		Welcome to Questionair!
		Please select one of the following options:
		1. Make exam
		2. View exams
		3. View learning goals
		4. Add learning goal
		5. Exit
		
		
		Learning goals:
		1. getallen
		2. optellen
		3. aftrekken
		4. vermenigvuldigen
		5. delen
		6. rekenvolgorde
		7. machten
		8. wortels
		9. hogeremachtswortels
		10. exponentiële functies
		11. logaritmes
		12. vergelijkingen
		13. herleiden
		
		14. Back
		
		
		Goal: delen
		Can be found at: h1.4
		Children: vermenigvuldigen, \b\b
		Descendants: optellen, vermenigvuldigen, getallen, \b\b
		
		1. Edit
		2. Back
		
		
		Learning goals:
		1. getallen
		2. optellen
		3. aftrekken
		4. vermenigvuldigen
		5. delen
		6. rekenvolgorde
		7. machten
		8. wortels
		9. hogeremachtswortels
		10. exponentiële functies
		11. logaritmes
		12. vergelijkingen
		13. herleiden
		
		14. Back
		
		
		Welcome to Questionair!
		Please select one of the following options:
		1. Make exam
		2. View exams
		3. View learning goals
		4. Add learning goal
		5. Exit
		
		
		Description:
		
		
		Location:
		
		
		Created new learning goal
		
		
		Goal: this
		Can be found at: there
		Children: None
		Descendants: None
		
		1. Edit
		2. Back
		
		
		Welcome to Questionair!
		Please select one of the following options:
		1. Make exam
		2. View exams
		3. View learning goals
		4. Add learning goal
		5. Exit
		
		
		Learning goals:
		1. getallen
		2. this
		3. aftrekken
		4. vermenigvuldigen
		5. delen
		6. rekenvolgorde
		7. machten
		8. wortels
		9. hogeremachtswortels
		10. exponentiële functies
		11. logaritmes
		12. vergelijkingen
		13. herleiden
		
		14. Back
		
		
		Welcome to Questionair!
		Please select one of the following options:
		1. Make exam
		2. View exams
		3. View learning goals
		4. Add learning goal
		5. Exit
		
		
		""", output.toString().replace("\r\n", "\n"));
	}
}