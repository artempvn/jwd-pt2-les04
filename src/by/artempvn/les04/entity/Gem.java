package by.artempvn.les04.entity;

import java.time.LocalDateTime;

public class Gem {

	public enum GemType {
		PRECIOUS, SEMIPRECIOUS;
	}

	private String id;
	private String origin;
	private String name;
	private int value;
	private LocalDateTime cutDate;
	private Parameter parameters = new Parameter();
	private GemType preciousness;

	public Gem() {
	}

	public Gem(String id, String origin, String name, int value,
			Parameter parameters, LocalDateTime cutDate,GemType preciousness) {
		this.id = id;
		this.origin = origin;
		this.name = name;
		this.value = value;
		this.parameters = parameters;
		this.cutDate = cutDate;
		this.preciousness=preciousness;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public LocalDateTime getCutDate() {
		return cutDate;
	}

	public void setCutDate(LocalDateTime cutDate) {
		this.cutDate = cutDate;
	}

	public Parameter getParameters() {
		return parameters;
	}

	public void setParameters(Parameter parameters) {
		this.parameters = parameters;
	}

	public GemType getPreciousness() {
		return preciousness;
	}

	public void setPreciousness(GemType gemType) {
		this.preciousness = gemType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cutDate == null) ? 0 : cutDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result
				+ ((preciousness == null) ? 0 : preciousness.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Gem other = (Gem) obj;
		if (cutDate == null) {
			if (other.cutDate != null) {
				return false;
			}
		} else if (!cutDate.equals(other.cutDate)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (origin == null) {
			if (other.origin != null) {
				return false;
			}
		} else if (!origin.equals(other.origin)) {
			return false;
		}
		if (parameters == null) {
			if (other.parameters != null) {
				return false;
			}
		} else if (!parameters.equals(other.parameters)) {
			return false;
		}
		if (preciousness != other.preciousness) {
			return false;
		}
		if (value != other.value) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nGem \nid: ").append(id).append("\norigin: ")
				.append(origin).append("\nname: ").append(name)
				.append("\nvalue: ").append(value).append("\ncut-date: ")
				.append(cutDate).append("\nparameters: ").append(parameters)
				.append("\ngemType: ").append(preciousness).append("\n");
		return builder.toString();
	}

	public static class Parameter {
		private String color;
		private int transparency;
		private int numberOfFaces;

		public Parameter() {
		}

		public Parameter(String color, int transparency, int numberOfFaces) {
			this.color = color;
			this.transparency = transparency;
			this.numberOfFaces = numberOfFaces;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public int getTransparency() {
			return transparency;
		}

		public void setTransparency(int transparency) {
			this.transparency = transparency;
		}

		public int getNumberOfFaces() {
			return numberOfFaces;
		}

		public void setNumberOfFaces(int numberOfFaces) {
			this.numberOfFaces = numberOfFaces;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((color == null) ? 0 : color.hashCode());
			result = prime * result + numberOfFaces;
			result = prime * result + transparency;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Parameter other = (Parameter) obj;
			if (color == null) {
				if (other.color != null) {
					return false;
				}
			} else if (!color.equals(other.color)) {
				return false;
			}
			if (numberOfFaces != other.numberOfFaces) {
				return false;
			}
			if (transparency != other.transparency) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("\n\tcolor: ").append(color)
					.append("\n\ttransparency: ").append(transparency)
					.append("\n\tnumber-of-faces: ").append(numberOfFaces);
			return builder.toString();
		}
	}

}
