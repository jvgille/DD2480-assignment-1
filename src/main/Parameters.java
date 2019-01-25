package decide;

public final class Parameters {
	public double length1;
	public double radius1;
	public double epsilon;
	public double area1;
	public int q_pts;
	public int quads;
	public double dist;
	public int n_pts;
	public int k_pts;
	public int a_pts;
	public int b_pts;
	public int c_pts;
	public int d_pts;
	public int e_pts;
	public int f_pts;
	public int g_pts;
	public double length2;
	public double radius2;
	public double area2;

	public Parameters() {

		this.length1 = 0.0;
		this.radius1 = 0.0;
		this.epsilon = 0.0;
		this.area1 = 0.0;
		this.q_pts = 0;
		this.quads = 0;
		this.dist = 0.0;
		this.n_pts = 0;
		this.k_pts = 0;
		this.a_pts = 0;
		this.b_pts = 0;
		this.c_pts = 0;
		this.d_pts = 0;
		this.e_pts = 0;
		this.f_pts = 0;
		this.g_pts = 0;
		this.length2 = 0.0;
		this.radius2 = 0.0;
		this.area2 = 0.0;
	}

	public Parameters(double length1, double radius1, double epsilon, double area1,
			int q_pts, int quads, double dist, int n_pts, int k_pts, int a_pts, int b_pts,
			int c_pts, int d_pts, int e_pts, int f_pts, int g_pts, double length2, double radius2, double area2) {

		this.length1 = length1;
		this.radius1 = radius1;
		this.epsilon = epsilon;
		this.area1 = area1;
		this.q_pts = q_pts;
		this.quads = quads;
		this.dist = dist;
		this.n_pts = n_pts;
		this.k_pts = k_pts;
		this.a_pts = a_pts;
		this.b_pts = b_pts;
		this.c_pts = c_pts;
		this.d_pts = d_pts;
		this.e_pts = e_pts;
		this.f_pts = f_pts;
		this.g_pts = g_pts;
		this.length2 = length2;
		this.radius2 = radius2;
		this.area2 = area2;
	}


}
