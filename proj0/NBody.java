
public class NBody {
    private static String backgroundImage = "./images/starfield.jpg";
    
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int numberplanet = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[numberplanet];
        for (int i = 0; i < numberplanet; i++){            
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                            in.readDouble(),in.readDouble(), in.readString());
            }
            return planets;
    }         
      
    
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
       
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        StdDraw.setScale(-radius, radius); 
        /**time variable */
        double t = 0.0;
        int n = planets.length;
        double[] xForces = new double[n];
        double[] yForces = new double[n];
        
        while (t < T){
            /**xForces and yForces */
            
            for (int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcForceExertedByX(planets[i]);
                yForces[i] = planets[i].calcForceExertedByY(planets[i]);                
            }
            for (int j = 0; j< planets.length; j++){
                planets[j].update(dt,xForces[j],yForces[j]);
            }                       
            /**draw background*/
                  
            StdDraw.picture(0, 0, NBody.backgroundImage);           
            /**draw planet */
            for(Planet planet: planets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;

        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
    }
}
        

                    
           




        
    

    