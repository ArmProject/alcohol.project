package com.project.alcoholproject.game;

import java.util.ArrayList;
import java.util.Random;

import android.R.integer;
import android.util.Log;

public class Algorithm {
	// ArrayList<Integer> row[];
	Row row[];
	int n, d;
	int current = 0;
	boolean isWin = false;

	public Algorithm(int d, int n) {
		this.d = d;
		this.n = n / d;
		row = new Row[d];
		add();
		random();
	}

	public int[] getData() {
		// ArrayList<Integer> data = new ArrayList<Integer>();
		int data[] = new int[d * n];
		for (int i = 0; i < d; i++) {
			// data.addAll(row[i].data);
			for (int j = 0; j < n; j++) {
				data[i * d + j] = row[i].data.get(j);
			}
		}
		// return (Integer[]) data.toArray();
		return data;
	}

	public void set(int i) {
		current = i;
		if (i == n) {
			isWin = true;
		}
	}

	public boolean isWin() {
		return isWin;
	}

	public boolean check(int i) {
		return i == current + 1 || i == current;
		// for (int i = 0; i < row.length; i++) {
		// String msg = "";
		// for (int arr : row[i].data) {
		// msg += arr + " ";
		// }
		// Log.e("test", msg);
		// }
	}

	public void random() {
		Random rnd = new Random();
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < n; j++) {
				swap(row[i].data, j, rnd.nextInt(n));
			}
		}
	}

	private void add() {
		for (int i = 0; i < d * n; i++) {
			int k = i % d;
			if (row[k] == null) {
				row[k] = new Row();
			}
			row[k].data.add(i);
		}
	}

	private void swap(ArrayList<Integer> list, int i, int j) {
		int tmp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, tmp);
	}

	class Row {
		public ArrayList<Integer> data = new ArrayList<Integer>();
	}
}
