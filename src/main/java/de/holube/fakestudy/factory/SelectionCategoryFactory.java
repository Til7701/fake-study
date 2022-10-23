package de.holube.fakestudy.factory;

import de.holube.fakestudy.Study;
import de.holube.fakestudy.category.Category;
import de.holube.fakestudy.category.SelectionCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelectionCategoryFactory extends AbstractCategoryFactory {

    private List<String> selection;
    private int min;
    private int max;
    private String missingValue;

    protected Category createCategory(Study study) {
        SelectionCategory selectionCategory = new SelectionCategory();
        selectionCategory.getSelection().addAll(selection);
        selectionCategory.setMin(min);
        selectionCategory.setMax(max);
        selectionCategory.setMissingValue(missingValue);
        return selectionCategory;
    }

}
