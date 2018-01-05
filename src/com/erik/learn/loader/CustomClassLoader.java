package com.erik.learn.loader;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {

	public CustomClassLoader(ClassLoader parent) {
		super(parent);
	}

	private Class getClass(String name) throws ClassNotFoundException {
		String file = name.replace('.', File.separatorChar) + ".class";
		byte[] b = null;
		try {
			b = loadClassFileData(file);
			Class c = defineClass(name, b, 0, b.length);
			resolveClass(c);
			return c;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Class loadClass(String name) throws ClassNotFoundException {
		System.out.println("Loading Class '" + name + "'");
		if (name.startsWith("com.erik.learn")) { // this conditional is important to avoid class not found by loader
			System.out.println("Loading Class using CCLoader");
			return getClass(name);
		}
		
		return super.loadClass(name);
	}

	private byte[] loadClassFileData(String name) throws IOException {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
		int size = stream.available();
		byte buff[] = new byte[size];
		DataInputStream in = new DataInputStream(stream);
		in.readFully(buff);
		in.close();
		return buff;
	}
}
