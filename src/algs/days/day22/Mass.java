package algs.days.day22;

import java.io.IOException;
import java.net.URL;

import algs.hw4.map.*;

public class Mass {
	public static void main(String[] args) throws IOException {
		
		URL map = HighwayMap.class.getResource("/algs/hw4/resources/MA-region-simple.tmg");
		Information info = HighwayMap.undirectedGraph(map.openStream());

		// you might find this file useful....
		new BFSMapAnimation(info).launch(0, info.labels.size()-1);
	}
}
