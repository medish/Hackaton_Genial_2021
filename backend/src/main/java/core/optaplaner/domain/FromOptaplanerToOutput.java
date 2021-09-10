package core.optaplaner.domain;

import core.output.IOutput;

public interface FromOptaplanerToOutput<T extends IOutput> {

    T toOutput();
}
