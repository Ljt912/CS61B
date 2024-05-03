//import java.math.*;
public class Planet {
        public double xxPos;
        public double yyPos;
        public double xxVel;
        public double yyVel;
        public double mass;
        public String imgFileName;

	/** constructor */
	public Planet(double xP, double yP, double xV,
			double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}


	/** copy constructor */
	public Planet(Planet b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}


	public double calcDistance(Planet other) {
		return Math.sqrt(Math.pow((this.xxPos - other.xxPos), 2) +
							Math.pow((this.yyPos - other.yyPos), 2));
	}


	public double calcForceExertedBy(Planet other) {
		final double G = 6.67e-11;
		return G * this.mass * other.mass /
					Math.pow(this.calcDistance(other), 2);
	}


	public double calcForceExertedByX(Planet other) {
		return calcForceExertedBy(other) * (other.xxPos - this.xxPos) /
					calcDistance(other);
	}


	public double calcForceExertedByY(Planet other) {
		return calcForceExertedBy(other) * (other.yyPos - this.yyPos) /
					calcDistance(other);
	}


	public double calcNetForceExertedByX(Planet[] others) {
		double netForceExertedByX = 0;
		for (Planet other : others) {
			if (this.equals(other)) {
				continue;
			}
			netForceExertedByX += calcForceExertedByX(other);
		}
		return netForceExertedByX;
	}


	public double calcNetForceExertedByY(Planet[] others) {
		double netForceExertedByY = 0;
		for (Planet other : others) {
			if (this.equals(other)) {
				continue;
			}
			netForceExertedByY += calcForceExertedByY(other);
		}
		return netForceExertedByY;
	}

	public void update(double dt, double NetForceExertedByX, double NetForceExertedByY) {
		double accelerationOnX = NetForceExertedByX / this.mass;
		double accelerationOnY = NetForceExertedByY / this.mass;
		this.xxVel += dt * accelerationOnX;
		this.yyVel += dt * accelerationOnY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}
