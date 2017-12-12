public class Robot {
    private Double pos_X;
    private Double pos_Y;
    private Double signalA;
    private Double signalB;
    private Double signalC;
    private Double toA;
    private Double toB;
    private Double toC;
    public Robot(Double x, Double y){
        this.pos_X=x;
        this.pos_Y=y;
    }

    public void setToA(Double aX,Double aY) {
        this.toA = Math.sqrt(Math.pow(aX-pos_X,2)+Math.pow(aY-this.pos_Y,2));
        this.signalA=-2*this.toA;
    }
    public void setToB(Double bX,Double bY) {
        this.toB = Math.sqrt(Math.pow(bX-pos_X,2)+Math.pow(bY-this.pos_Y,2));
        this.signalB=-2*this.toB;
    }
    public void setToC(Double cX,Double cY) {
        this.toC = Math.sqrt(Math.pow(cX-pos_X,2)+Math.pow(cY-this.pos_Y,2));
        this.signalC=-2*this.toC;
    }

    public Double getSignalA() {
        return signalA;
    }

    public Double getSignalB() {
        return signalB;
    }

    public Double getSignalC() {
        return signalC;
    }
}