package com.erik.learn;

import java.lang.reflect.Method;

import com.erik.learn.loader.CustomClassLoader;

public class CustomClassLoaderRun {
	public static void main(String args[]) throws Exception {
		String progClass = args[0];
		String progArgs[] = new String[args.length - 1];
		System.arraycopy(args, 1, progArgs, 0, progArgs.length);

		CustomClassLoader ccl = new CustomClassLoader(CustomClassLoaderRun.class.getClassLoader());
		Class clas = ccl.loadClass(progClass);
		Class mainArgType[] = { (new String[0]).getClass() };
		Method main = clas.getMethod("main", mainArgType);
		Object argsArray[] = { progArgs };
		main.invoke(null, argsArray);

		Method printCL = clas.getMethod("printCL", null);
		printCL.invoke(null, new Object[0]);
	}
}
