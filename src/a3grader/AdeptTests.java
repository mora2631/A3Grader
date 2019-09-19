package a3grader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import a3.CompletedRide;
import a3.ShortButFairDispatcher;
import a3.ShortestWaitDispatcher;
import a3.Simulation;

class AdeptTests {

	@Test
	void shortestWaitDispatcherTest() {
		Simulation sim = new Simulation(10, 20, new ShortestWaitDispatcher());

		CompletedRide[] ride_log = sim.getRideLog();

		assertEquals(64.10, ride_log[0].getProfit(), 0.005);
		assertEquals(257.60, ride_log[1].getProfit(), 0.005);
		assertEquals(119.70, ride_log[2].getProfit(), 0.005);
		assertEquals(73.60, ride_log[3].getProfit(), 0.005);
		assertEquals(49.00, ride_log[4].getProfit(), 0.005);
		assertEquals(230.00, ride_log[5].getProfit(), 0.005);
		assertEquals(71.80, ride_log[6].getProfit(), 0.005);
		assertEquals(139.30, ride_log[7].getProfit(), 0.005);
		assertEquals(262.30, ride_log[8].getProfit(), 0.005);
		assertEquals(153.00, ride_log[9].getProfit(), 0.005);
		assertEquals(148.40, ride_log[10].getProfit(), 0.005);
		assertEquals(187.10, ride_log[11].getProfit(), 0.005);
		assertEquals(233.40, ride_log[12].getProfit(), 0.005);
		assertEquals(125.00, ride_log[13].getProfit(), 0.005);
		assertEquals(204.90, ride_log[14].getProfit(), 0.005);
		assertEquals(71.40, ride_log[15].getProfit(), 0.005);
		assertEquals(169.90, ride_log[16].getProfit(), 0.005);
		assertEquals(57.90, ride_log[17].getProfit(), 0.005);
		assertEquals(274.40, ride_log[18].getProfit(), 0.005);
		assertEquals(158.80, ride_log[19].getProfit(), 0.005);
	}
	
	@Test
	void shortButFairDispatcherTest() {
		Simulation sim = new Simulation(1, 20, new ShortButFairDispatcher());

		CompletedRide[] ride_log = sim.getRideLog();

		assertEquals(49.20, ride_log[0].getProfit(), 0.005);
		assertEquals(83.40, ride_log[1].getProfit(), 0.005);
		assertEquals(78.10, ride_log[2].getProfit(), 0.005);
		assertEquals(88.50, ride_log[3].getProfit(), 0.005);
		assertEquals(35.90, ride_log[4].getProfit(), 0.005);
		assertEquals(73.50, ride_log[5].getProfit(), 0.005);
		assertEquals(5.90, ride_log[6].getProfit(), 0.005);
		assertEquals(71.30, ride_log[7].getProfit(), 0.005);
		assertEquals(124.60, ride_log[8].getProfit(), 0.005);
		assertEquals(136.00, ride_log[9].getProfit(), 0.005);
		assertEquals(135.30, ride_log[10].getProfit(), 0.005);
		assertEquals(138.20, ride_log[11].getProfit(), 0.005);
		assertEquals(107.60, ride_log[12].getProfit(), 0.005);
		assertEquals(84.20, ride_log[13].getProfit(), 0.005);
		assertEquals(46.50, ride_log[14].getProfit(), 0.005);
		assertEquals(233.30, ride_log[15].getProfit(), 0.005);
		assertEquals(239.90, ride_log[16].getProfit(), 0.005);
		assertEquals(158.00, ride_log[17].getProfit(), 0.005);
		assertEquals(34.40, ride_log[18].getProfit(), 0.005);
		assertEquals(15.80, ride_log[19].getProfit(), 0.005);
	}
	

}
