package com.flatironschool.javacs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.jsoup.select.Elements;

public class WikiPhilosophy {
	
	final static WikiFetcher wf = new WikiFetcher();
	
	/**
	 * Tests a conjecture about Wikipedia and Philosophy.
	 * 
	 * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
	 * 
	 * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
        // some example code to get you started

		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		Elements paragraphs = wf.fetchWikipedia(url);

		Element firstPara = paragraphs.get(0);
		
		Iterable<Node> iter = new WikiNodeIterable(firstPara);
		for (Node node: iter) {
			if (node instanceof TextNode) {
				//System.out.print(node);
			}
        }
        // TODO remove this line 
        // the following throws an exception so the test fails
        // until you update the code
        //String msg = "Complete this lab by adding your code and removing this statement.";
        //throw new UnsupportedOperationException(msg);

        // Start of implementation
        
        // Add the beginning URL to our visited set and add to our output
        HashSet<String> visited = new HashSet<>();
        visited.add(url);
        List<String> output = new ArrayList<>();
        output.add(url);
        wikiSearch(url, visited, output);  
        System.out.println(output);
	}

	public static void wikiSearch(String url, HashSet<String> visited, List<String> output) {
		for (Element current: paragraphs) {
			Iterable<Node> iter2 = new WikiNodeIterable(current);
			for (Node node: iter2) {
				if (node instanceof Element) {
					Element currElement = (Element) node;
					String tag = currElement.tagName();
					String currUrl = currElement.text();
					if (tag.equals("a")) {
						if (validLink(currElement, visited)) {
							output.add(currUrl);
							wikiSearch(currUrl, visited, output);
						}
					}
				}
        	}
        }
	}

	public static boolean validLink(Element current, HashSet<String> visited) {









	}
}
