package polynoms;

import static polynoms.Separator.LEFT;
import static polynoms.Separator.RIGHT;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Dmytro Legeza
 */
public class Process {

	public void simplify(final String incoming, final int value) {
		System.out.println("--------------------- STARTING ---------------------");
		System.out.println("Incoming polynomial: " + incoming);
		System.out.println("... processing started ...");
		Polynomial res = process(incoming);
		System.out.println("Simplified polynomial: " + res);
		Number calculationResult = res.evaluate(value);
		System.out.println("Evaluation result: " + calculationResult);
		System.out.println("--------------------- FINISHED ---------------------");
	}

	private Polynomial process(String incoming) {
		if (!incoming.contains(LEFT.getSeparator()) && !incoming.contains(RIGHT.getSeparator())) {
			return Polynomial.compose(incoming);
		}
		while (incoming.contains(LEFT.getSeparator()) || incoming.contains(RIGHT.getSeparator())) {
			incoming = resolveLayerByLayer(incoming);
		}
		return Polynomial.compose(incoming);
	}

	private String resolveLayerByLayer(final String incoming) {
		Map<Integer, List<Interval>> parensesisMask = createMask(incoming);
		int maxDepthToResolveFirst = Collections.max(parensesisMask.keySet());
		// resolve inner parensesis to polynomials
		List<Interval> inners = parensesisMask.get(maxDepthToResolveFirst);
		Map<String, Pair<Interval,Polynomial>> transformed = new HashMap<>();
		int count = 1;
		int offsetToExcludeParensesis = 1;
		for(Interval inner: inners) {
			final String polynomialName = "inner" + maxDepthToResolveFirst + "" + count;
			transformed.put(polynomialName,
				new Pair<>(inner, Polynomial.compose(incoming.substring(inner.getStart() + offsetToExcludeParensesis, inner.getEnd()))));
			++ count;
		}
//		 find outer parensesis that contain inner ones
		List<Interval> outers= parensesisMask.get(maxDepthToResolveFirst - 1);
		List<Interval> affecteds = new LinkedList<>();
		// check if no outer parensesis
		if (outers == null) {
			Interval interval = new Interval(0, incoming.length());
			affecteds.add(interval);
		} else {
			for (Interval outer: outers) {
				for (Interval inner: inners) {
					if (outer.contains(inner) && !affecteds.contains(outer)) {
						affecteds.add(outer);
					}
				}
			}
		}
		// change only one outer at a time
		Interval affected = affecteds.get(0);
		String replacedAffectedPart ="";
		int prevEnd = 0;
		boolean firstFound = false;
		for (String key: asSortedList(transformed.keySet())) {
			Pair<Interval, Polynomial> pair = transformed.get(key);
			Interval inner = pair.getV();
			if (affected.contains(inner) && !firstFound) {
				firstFound = true;
				replacedAffectedPart = incoming.substring(0, inner.getStart()) + findPolynomialName(transformed, inner);
				prevEnd = inner.getEnd();
			} else {
				replacedAffectedPart += incoming.substring(prevEnd + offsetToExcludeParensesis, inner.getStart()) + findPolynomialName(transformed, inner);
				prevEnd = inner.getEnd();
			}
		}
		replacedAffectedPart += incoming.substring(prevEnd + offsetToExcludeParensesis);
		Map<Integer, List<Interval>> replacedParensesisMask = createMask(replacedAffectedPart);
		final Polynomial polynomial;
		if (replacedParensesisMask.size() != 0) {
			// got replaced interval in new string
			Interval affectedIntervalAfterReplacement = findProperIntervalAfterReplacement(replacedParensesisMask, affected);
			// got outer part to transform to polynomial
			String extractedReplacedSubString = replacedAffectedPart.substring(affectedIntervalAfterReplacement.getStart() + offsetToExcludeParensesis, affectedIntervalAfterReplacement.getEnd());
			polynomial = Polynomial.compose(extractedReplacedSubString, toKeyValue(transformed));
			return incoming.substring(0, affected.getStart() + offsetToExcludeParensesis) + polynomial + incoming.substring(affected.getEnd());
		} else {
			polynomial = Polynomial.compose(replacedAffectedPart, toKeyValue(transformed));
			return polynomial.toString();
		}
	}


	private String findPolynomialName(final Map<String, Pair<Interval,Polynomial>> transformed, final Interval interval) {
		for (String inner: transformed.keySet()) {
			Interval innerInterval = transformed.get(inner).getV();
			if (innerInterval.equals(interval)) {
				return inner;
			}
		}
		return null;
	}

	private Map<String, Polynomial> toKeyValue(final Map<String, Pair<Interval,Polynomial>> transformed) {
		Map<String, Polynomial> map = new HashMap<>();
		for (String key: transformed.keySet()) {
			map.put(key, transformed.get(key).getT());
		}
		return map;
	}

	private Interval findProperIntervalAfterReplacement(final Map<Integer, List<Interval>> replacedParensesisMasks, final Interval affected) {
		for (Integer layer: replacedParensesisMasks.keySet()) {
			List<Interval> intervals = replacedParensesisMasks.get(layer);
			for (Interval interval: intervals) {
				if (interval.getStart() == affected.getStart()) {
					return interval;
				}
			}
		}
		return null;
	}

	private Map<Integer, List<Interval>> createMask(final String incoming) {
		String[] splitted = incoming.trim().split("");
		Map<Integer, List<Interval>> ret = new HashMap<>();
		Map<Integer, Interval> current = new HashMap<>();
		int allLettersCount = -1;
		int count = 0;
		for (int i = 1; i < splitted.length; i++) {
			String character = splitted[i];
			++ allLettersCount;
			if (LEFT.getSeparator().equals(character)) {
				++count;
				current.put(count, new Interval(allLettersCount));
			} else if (RIGHT.getSeparator().equals(character)) {
				Interval interval = current.remove(count);
				interval.setEnd(allLettersCount);
				if (!ret.containsKey(count)) {
					ret.put(count, new LinkedList<Interval>());
				}
				List<Interval> allIntervalsOfSameDepth = ret.get(count);
				allIntervalsOfSameDepth.add(interval);
				-- count;
			}
		}
		return ret;
	}

	private static <T extends Comparable<? super T>> List<T> asSortedList(final Collection<T> c) {
		List<T> list = new LinkedList<>(c);
		java.util.Collections.sort(list);
		return list;
	}
}
