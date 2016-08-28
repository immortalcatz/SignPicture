package com.kamesuta.mc.signpic.shortening;

import com.kamesuta.mc.signpic.util.Downloader;

public abstract class IShorteningMain extends Thread implements IShortening {

	protected String rawurl;
	protected IShorteningCallback callback;

	public static Downloader downloader = new Downloader();

	@Override
	public void shortening(final String rawurl, final IShorteningCallback callback) {
		this.rawurl = rawurl;
		this.callback = callback;

		setName("Sign Picture Shortening URL Thread");
		start();
	}

	@Override
	public abstract void run();

	@Override
	public abstract String getServicename();
}
