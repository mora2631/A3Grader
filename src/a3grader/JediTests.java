package a3grader;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;

import a3.Dispatcher;
import a3.Simulation;

class JediTests {

	@Test
	void simulationConstructorTest() {
		Constructor[] constructors = Simulation.class.getConstructors();
		
		assertEquals(2, constructors.length);
		Class[] orig_constructor_parameter_types = null;
		Class[] general_constructor_parameter_types = null;
		
		if (constructors[0].getParameterCount() == 3) {
			orig_constructor_parameter_types = constructors[0].getParameterTypes();
			assertEquals(7, constructors[1].getParameterCount());
			general_constructor_parameter_types = constructors[1].getParameterTypes();
		} else {
			assertEquals(7, constructors[0].getParameterCount());
			general_constructor_parameter_types = constructors[0].getParameterTypes();
			assertEquals(3, constructors[1].getParameterCount());
			orig_constructor_parameter_types = constructors[1].getParameterTypes();
		}
		
		assertEquals(long.class, orig_constructor_parameter_types[0]);
		assertEquals(int.class, orig_constructor_parameter_types[1]);
		assertEquals(Dispatcher.class, orig_constructor_parameter_types[2]);
		
		assertEquals(long.class, general_constructor_parameter_types[0]);
		assertEquals(int.class, general_constructor_parameter_types[1]);
		assertEquals(Dispatcher.class, general_constructor_parameter_types[2]);
		assertEquals(int.class, general_constructor_parameter_types[3]);
		assertEquals(int.class, general_constructor_parameter_types[4]);
		assertEquals(int.class, general_constructor_parameter_types[5]);
		assertEquals(int.class, general_constructor_parameter_types[6]);
	}

}
