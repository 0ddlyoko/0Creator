package me.oddlyoko.zeroCreator.composant;

public abstract class Composant implements IComposant {
	private ComposantEvent ce;

	public void setComposantEvent(ComposantEvent ce) {
		this.ce = ce;
	}

	public ComposantEvent getComposantEvent() {
		return ce;
	}

	public void event() {
		if (ce != null)
			ce.onEvent();
	}

	public interface ComposantEvent {
		public void onEvent();
	}
}
