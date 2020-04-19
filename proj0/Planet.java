public class Planet {
    //instance variables
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    //Constructor
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
        
    }
    /**Calculate distance btw two planets */
    public double calcDistance(Planet p){
        double xDiff = xxPos - p.xxPos;
        double yDiff = yyPos - p.yyPos;
        double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        return distance;
    }
    /**takes in a planet, and returns a double describing the force exerted on this planet by the given planet */
    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        double force = (6.67e-11 * this.mass *p.mass) / (r *r);
        return force;
    }
    /**Force in the x and y direction */
    public double calcForceExertedByX(Planet p){
        double x_force = calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
        return x_force;
    }
    public double calcForceExertedByY(Planet p){
        double y_force = calcForceExertedBy(p) * (p.yyPos - this.yyPos)/ calcDistance(p);
        return y_force;
    }
    public double calcNetForceExertedByX(Planet[] p){
        double sumx = 0;
        for (int i = 0; i < p.length; i++){
            if (this.equals(p[i])){
                continue;
            }
            sumx += calcForceExertedByX(p[i]);
        }
        return sumx;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double sumy = 0;
        for (int i = 0; i < p.length; i++){
            if (this.equals(p[i])){
                continue;
            }
            sumy += calcForceExertedByY(p[i]);
        }
        return sumy;
    }
    public void update(double dt, double fX,double fY){
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel = this.xxVel + ax * dt;
        this.yyVel = this.yyVel + ay * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
               
    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"./images/"+imgFileName);
    }

    
}