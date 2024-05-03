public class NBody {
    
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        double Radius = in.readDouble();
        Planet[] Planets = new Planet[n];
        for (int i = 0; i < n; i ++) {
            Planets[i] = new Planet(in.readDouble(), in.readDouble(), 
                                in.readDouble(), in.readDouble(),
                                in.readDouble(), in.readString());
        }

        return Planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double Radius =  readRadius(filename);
        Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-Radius, Radius);
		StdDraw.enableDoubleBuffering();

        
        double t = 0;
        int n = planets.length;
        while(t < T) {
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            
            for (int i = 0; i < n; i ++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < n; i ++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < planets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }

    }
}