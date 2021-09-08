package core.optaplaner.domain;

import core.output.Output;

public interface FromOptaplanerToOutput<T extends Output> {

	T toOutput();
}
