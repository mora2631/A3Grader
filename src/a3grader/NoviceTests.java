package a3grader;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import a3.CompletedRide;
import a3.CompletedRideImpl;
import a3.Driver;
import a3.DriverImpl;
import a3.Position;
import a3.PositionImpl;
import a3.RideRequest;
import a3.RideRequestImpl;
import a3.Vehicle;
import a3.VehicleImpl;

public class NoviceTests {

	@Test
	void positionTest() {
		Position a = new PositionImpl(0, 0);
		
		assertEquals(0, a.getX(), "Expected x coordinate to be 0");
		assertEquals(0, a.getY(), "Expected y coordinate to be 0");
		
		Position b = new PositionImpl(5, 8);

		assertEquals(5, b.getX(), "Expected x coordinate to be 5");
		assertEquals(8, b.getY(), "Expected y coordinate to be 8");

		assertEquals(13, a.getManhattanDistanceTo(b), "Expected Manhattan distance to be 13");
		assertEquals(13, b.getManhattanDistanceTo(a), "Expected Manhattan from b to a to be same as from a to b");
	}
	
	@Test
	void basicDriverTest() {
		Position p = new PositionImpl(1,2);
		Vehicle v = new VehicleImpl("Ford", "Focus", "ABC-123", p);
		Driver d = new DriverImpl("John", "Smith", 1234, v);
		
		assertEquals("John", d.getFirstName());
		assertEquals("Smith", d.getLastName());
		assertEquals("John Smith", d.getFullName());
		assertEquals(1234, d.getID());
		assertEquals(v, d.getVehicle());
	}
	
	@Test
	void driverSetterTest() {
		Position p = new PositionImpl(1,2);
		Vehicle v1 = new VehicleImpl("Ford", "Focus", "ABC-123", p);
		Vehicle v2 = new VehicleImpl("Chevy", "Malibu", "123-ABC", p);
		Driver d = new DriverImpl("John", "Smith", 1234, v1);
		
		assertEquals(v1, d.getVehicle());
		
		d.setVehicle(v2);

		assertEquals(v2, d.getVehicle());
	}
	
	@Test
	void driverExceptionsTest() {
		Position p = new PositionImpl(1,2);
		Vehicle v = new VehicleImpl("Ford", "Focus", "ABC-123", p);

		try {
			Driver d = new DriverImpl(null, "Smith", 1234, v);
			fail("Expected runtime exception to be thrown for null first name");
		} catch (RuntimeException e) {
		}

		try {
			Driver d = new DriverImpl("John", null, 1234, v);
			fail("Expected runtime exception to be thrown for null last name");
		} catch (RuntimeException e) {
		}

		try {
			Driver d = new DriverImpl("John", "Smith", 1234, null);
			fail("Expected runtime exception to be thrown for null vehicle");
		} catch (RuntimeException e) {
		}

		Driver d = new DriverImpl("John", "Smith", 1234, v);
		try {
			d.setVehicle(null);
			fail("Expected runtime exception to be thrown for null vehicle");
		} catch (RuntimeException e) {
		}
	}
	
	@Test
	void basicVehicleTest() {
		Position p = new PositionImpl(1,2);
		Vehicle v = new VehicleImpl("Ford", "Focus", "ABC-123", p);
		
		assertEquals("Ford", v.getMake());
		assertEquals("Focus", v.getModel());
		assertEquals("ABC-123", v.getPlate());
		assertEquals(0, v.getMileage());
		assertEquals(p, v.getPosition());
	}
	
	@Test
	void vehicleMoveTest() {
		Position p1 = new PositionImpl(1,2);
		Position p2 = new PositionImpl(6,8);
		Vehicle v = new VehicleImpl("Ford", "Focus", "ABC-123", p1);
	
		assertEquals(0, v.getMileage());
		assertEquals(p1, v.getPosition());

		v.moveToPosition(p2);
		
		assertEquals(11, v.getMileage());
		assertEquals(p2, v.getPosition());
	}
	
	@Test
	void vehicleExceptionsTest() {
		Position p = new PositionImpl(1,2);
		
		try {
			Vehicle v = new VehicleImpl(null, "Focus", "ABC-123", p);
			fail("Expected runtime exception for null make");
		} catch (RuntimeException e) {
		}
		
		try {
			Vehicle v = new VehicleImpl("Ford", null, "ABC-123", p);
			fail("Expected runtime exception for null model");
		} catch (RuntimeException e) {
		}

		try {
			Vehicle v = new VehicleImpl("Ford", "Focus", null, p);
			fail("Expected runtime exception for null plate");
		} catch (RuntimeException e) {
		}

		try {
			Vehicle v = new VehicleImpl("Ford", "Focus", "ABC-123", null);
			fail("Expected runtime exception for null initial position");
		} catch (RuntimeException e) {
		}

		try {
			Vehicle v = new VehicleImpl("Ford", "Focus", "ABC-123", p);
			v.moveToPosition(null);
			fail("Expected runtime exception for null move to position");
		} catch (RuntimeException e) {
		}
	}
	
	@Test
	void basicRideRequestTest() {
		Position client_pos = new PositionImpl(3,4);
		Position dest_pos = new PositionImpl(1,0);
		
		RideRequest r = new RideRequestImpl(client_pos, dest_pos);
		
		assertEquals(client_pos, r.getClientPosition());
		assertEquals(dest_pos, r.getDestination());
		assertFalse(r.getIsComplete());
		assertEquals(6, r.getRideTime());
	}
	
	@Test
	void basicRideRequestCompleteTest() {
		Position client_pos = new PositionImpl(3,4);
		Position dest_pos = new PositionImpl(1,0);
		Position driver_pos = new PositionImpl(1,2);
		Vehicle v = new VehicleImpl("Ford", "Focus", "ABC-123", driver_pos);
		Driver d = new DriverImpl("John", "Smith", 1234, v);

		RideRequest r = new RideRequestImpl(client_pos, dest_pos);

		assertFalse(r.getIsComplete());
		assertEquals(6, r.getRideTime());

		CompletedRide cr1 = r.complete(d);
		assertEquals(client_pos, r.getClientPosition());
		assertEquals(dest_pos, r.getDestination());
		assertTrue(r.getIsComplete());
		assertEquals(6, r.getRideTime());

		CompletedRide cr2 = r.complete(d);
		assertEquals(cr1, cr2);
		assertEquals(client_pos, r.getClientPosition());
		assertEquals(dest_pos, r.getDestination());
		assertTrue(r.getIsComplete());
		assertEquals(6, r.getRideTime());		
	}
	
	@Test
	void advancedRideRequestCompleteTest() {
		Position client_pos = new PositionImpl(3,4);
		Position dest_pos = new PositionImpl(1,0);
		Position driver_pos = new PositionImpl(1,2);
		Vehicle v = new VehicleImpl("Ford", "Focus", "ABC-123", driver_pos);
		Driver d = new DriverImpl("John", "Smith", 1234, v);

		RideRequest r = new RideRequestImpl(client_pos, dest_pos);
		CompletedRide cr1 = r.complete(d);
		
		assertEquals(4, cr1.getWaitTime());
		assertEquals(dest_pos, v.getPosition());
		assertEquals(10, v.getMileage());

		CompletedRide cr2 = r.complete(d);
		assertEquals(dest_pos, v.getPosition());
		assertEquals(10, v.getMileage());
	}
	
	@Test
	void rideRequestExceptionsTest() {
		Position client_pos = new PositionImpl(3,4);
		Position dest_pos = new PositionImpl(1,0);

		try {
			RideRequest r = new RideRequestImpl(null, dest_pos);
			fail("Expected runtime exception for null client position");
		} catch(RuntimeException e) {
		}
		
		try {
			RideRequest r = new RideRequestImpl(client_pos, null);
			fail("Expected runtime exception for null destintation position");
		} catch(RuntimeException e) {
		}
		
		try {
			RideRequest r = new RideRequestImpl(client_pos, dest_pos);
			r.complete(null);
			fail("Expected runtime exception for trying to complete ride request with null driver");
		} catch(RuntimeException e) {
		}
	}
	
	@Test
	void basicCompletedRideTest() {
		Position client_pos = new PositionImpl(15,5);
		Position dest_pos = new PositionImpl(10,20);
		Position driver_pos = new PositionImpl(0,0);
		Vehicle v = new VehicleImpl("Ford", "Focus", "ABC-123", driver_pos);
		Driver d = new DriverImpl("John", "Smith", 1234, v);

		RideRequest req = new RideRequestImpl(client_pos, dest_pos);

		CompletedRide c = new CompletedRideImpl(req, d);
		
		assertEquals(req, c.getRequest());
		assertEquals(d, c.getDriver());
		assertEquals(20, c.getWaitTime());
		assertEquals(40, c.getTotalTime());
		assertEquals(2.5*20, c.getPrice(), 0.005);
		assertEquals(.5*20+.1*20, c.getCost(), 0.005);
		assertEquals(c.getPrice()-c.getCost(), c.getProfit(), 0.005);
		// Vehicle should not have actually moved because that
		// is done by complete() method in RideRequest and not 
		// a side effect of creating the completed ride request.
		assertEquals(driver_pos, v.getPosition());
		
		// Move the vehicle and check to make sure that wait time 
		// for completed ride does not change.
		v.moveToPosition(new PositionImpl(100,100));
		
		assertEquals(20, c.getWaitTime());
	}
	
	@Test
	void completedRidePriceTest() {
		Position driver_pos = new PositionImpl(0,0);
		Vehicle v = new VehicleImpl("Ford", "Focus", "ABC-123", driver_pos);
		Driver d = new DriverImpl("John", "Smith", 1234, v);

		RideRequest req = new RideRequestImpl(new PositionImpl(0,0), new PositionImpl(0,10));
		CompletedRide c = new CompletedRideImpl(req, d);		
		assertEquals(0, c.getWaitTime());
		assertEquals(2.5*10, c.getPrice(), 0.005);
		
		req = new RideRequestImpl(new PositionImpl(24,0), new PositionImpl(24,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(24, c.getWaitTime());
		assertEquals(2.5*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(-24,0), new PositionImpl(-24,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(24, c.getWaitTime());
		assertEquals(2.5*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(25,0), new PositionImpl(25,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(25, c.getWaitTime());
		assertEquals(2.0*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(-25,0), new PositionImpl(-25,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(25, c.getWaitTime());
		assertEquals(2.0*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(49,0), new PositionImpl(49,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(49, c.getWaitTime());
		assertEquals(2.0*10, c.getPrice(), 0.005);
		
		req = new RideRequestImpl(new PositionImpl(-49,0), new PositionImpl(-49,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(49, c.getWaitTime());
		assertEquals(2.0*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(50,0), new PositionImpl(50,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(50, c.getWaitTime());
		assertEquals(1.0*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(-50,0), new PositionImpl(-50,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(50, c.getWaitTime());
		assertEquals(1.0*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(99,0), new PositionImpl(99,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(99, c.getWaitTime());
		assertEquals(1.0*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(-99,0), new PositionImpl(-99,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(99, c.getWaitTime());
		assertEquals(1.0*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(100,0), new PositionImpl(100,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(100, c.getWaitTime());
		assertEquals(0.5*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(-100,0), new PositionImpl(-100,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(100, c.getWaitTime());
		assertEquals(0.5*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(150,0), new PositionImpl(150,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(150, c.getWaitTime());
		assertEquals(0.5*10, c.getPrice(), 0.005);

		req = new RideRequestImpl(new PositionImpl(-150,0), new PositionImpl(-150,10));
		c = new CompletedRideImpl(req, d);
		assertEquals(150, c.getWaitTime());
		assertEquals(0.5*10, c.getPrice(), 0.005);

	}
	
	@Test
	void completedRideExceptionsTest() {
		Position client_pos = new PositionImpl(15,5);
		Position dest_pos = new PositionImpl(10,20);
		Position driver_pos = new PositionImpl(0,0);
		Vehicle v = new VehicleImpl("Ford", "Focus", "ABC-123", driver_pos);
		Driver d = new DriverImpl("John", "Smith", 1234, v);

		RideRequest req = new RideRequestImpl(client_pos, dest_pos);

		try {
			CompletedRide c = new CompletedRideImpl(null, d);
			fail("Expected runtime exception for null ride request");
		} catch (RuntimeException e) {
		}
		
		try {
			CompletedRide c = new CompletedRideImpl(req, null);
			fail("Expected runtime exception for null driver");
		} catch (RuntimeException e) {
		}
	}
	
	@Test
	void defaultMethodsTest() {
		try {
			Method m = PositionImpl.class.getMethod("getManhattanDistanceTo", Position.class);
			assertEquals(true, m.isDefault());
		} catch(Exception e) {
			fail("Missing method");
		}
		
		try {
			Method m = DriverImpl.class.getMethod("getFullName", null);
			assertEquals(true, m.isDefault());
		} catch(Exception e) {
			fail("Missing method");
		}

		try {
			Method m = RideRequestImpl.class.getMethod("getRideTime", null);
			assertEquals(true, m.isDefault());
		} catch(Exception e) {
			fail("Missing method");
		}
		
		try {
			Method m = CompletedRideImpl.class.getMethod("getCost", null);
			assertEquals(true, m.isDefault());		
		} catch(Exception e) {
			fail("Missing method");
		}

		try {
			Method m = CompletedRideImpl.class.getMethod("getPrice", null);
			assertEquals(true, m.isDefault());		
		} catch(Exception e) {
			fail("Missing method");
		}

		try {
			Method m = CompletedRideImpl.class.getMethod("getProfit", null);
			assertEquals(true, m.isDefault());		
		} catch(Exception e) {
			fail("Missing method");
		}

		try {
			Method m = CompletedRideImpl.class.getMethod("getTotalTime", null);
			assertEquals(true, m.isDefault());		
		} catch(Exception e) {
			fail("Missing method");
		}

	}
	
}
