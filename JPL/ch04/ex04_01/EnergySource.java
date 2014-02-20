interface EnergySource {
	abstract void add(int volume);
	abstract void remove(int volume);
	abstract boolean empty();
}
