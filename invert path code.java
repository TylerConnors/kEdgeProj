private void invertPath( Node v2, int c, int d ) {
	ArrayList<Edge> path = findPath( v2, c, d );
	invertColors( path, c, d );
}

private ArrayList<Edge> findPath( Node v2, int c, int d ) {
	ArrayList<Edge> result = new ArrayList<Node>();
	if(c != d ) {
		Queue<Node> unprocessedVertices = new LinkedList<Node>();
		ArrayList<Node> processedVertices = new ArrayList<Node>();
		unprocessedVertices.add( v2 );
		
		Node previousVertex = null;
		while( !unprocessedVertices.isEmpty() ) {
			currentVertex = unprocessedVertices.remove();
			if( !processedVertices.contains( currentVertex ) ) {
				processedVertices.add( currentVertex );
				
				if( previousVertex != null ) { //Find the edge leading from the previous vertex to the current vertex
					Iterator<Edge> it = previousVertex.adj.iterator();
					while( it.hasNext() ) {
						Edge temp = it.next();
						if( temp.getOtherVertex( previousVertex ) == currentVertex ) {
							result.add( temp );
						}
					}
				}
				
				Iterator<Edge> it = currentVertex.adj.iterator();
				while( it.hasNext() ) {
					Edge temp = it.next();
					if( temp.color == c || temp.color == d ) {
						unprocessedVertices.add( temp );
					}
				}
			}
			
			previousVertex = currentVertex;
		}
	}
	return result;
}

private void invertColors( ArrayList<Edge> path, int c, int d ) {
	Iterator<Edge> it = path.iterator();
	while( it.hasNext() ) {
		Edge temp = it.next();
		if( temp.color == c ) {
			temp.color = d;
		} else {
			temp.color = c;
		}
	}
}
