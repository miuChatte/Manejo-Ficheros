package fileManagement;

import java.io.File;
import java.util.Objects;

public class Archivo {

	private String name;
	private long size;
	private long lastModified;
	
	
	public Archivo(File file) {
		super();
		this.name = file.getName();
		this.size = file.length();
		this.lastModified = file.lastModified();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getSize() {
		return size;
	}


	public void setSize(long size) {
		this.size = size;
	}


	public long getLastModified() {
		return lastModified;
	}


	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Archivo other = (Archivo) obj;
		return lastModified == other.lastModified && Objects.equals(name, other.name) && size == other.size;
	}


	@Override
	public String toString() {
		return "Archivo [name=" + name + ", size=" + size + ", lastModified=" + lastModified + "]";
	}
	
	
	
	
}
