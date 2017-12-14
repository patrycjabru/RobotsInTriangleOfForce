public class Robot {
    private Double pos_X;
    private Double pos_Y;
    private Double signalA;     //power of receiving signal
    private Double signalB;
    private Double signalC;
    private Double toA;         //distance
    private Double toB;
    private Double toC;
    public Robot(Double x, Double y){
        this.pos_X=x;
        this.pos_Y=y;
    }

    public void setToA(Double aX,Double aY, double amplitude) {
        this.toA = Math.sqrt(Math.pow(aX-pos_X,2)+Math.pow(aY-this.pos_Y,2));
//        this.signalA=-2*this.toA;
        this.signalA=amplitude/(Math.pow(this.toA,2));
    }
    public void setToB(Double bX,Double bY, double amplitude) {
        this.toB = Math.sqrt(Math.pow(bX-pos_X,2)+Math.pow(bY-this.pos_Y,2));
//        this.signalB=-2*this.toB;
        this.signalB=amplitude/(Math.pow(this.toB,2));
    }
    public void setToC(Double cX,Double cY, double amplitude) {
        this.toC = Math.sqrt(Math.pow(cX-pos_X,2)+Math.pow(cY-this.pos_Y,2));
//        this.signalC=-2*this.toC;
        this.signalC=amplitude/(Math.pow(this.toC,2));
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
    public Double getPos_X() {
        return pos_X;
    }

    public Double getPos_Y() {
        return pos_Y;
    }


}
