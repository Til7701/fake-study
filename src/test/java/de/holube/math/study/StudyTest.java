package de.holube.math.study;

import de.holube.math.study.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudyTest {

    final int defaultAmountSubjects = 42;

    Study study;

    String firstCategoryKey = "A";
    @Mock
    Category<Object> firstCategory;

    @BeforeEach
    void init() {
        study = new Study(defaultAmountSubjects);
    }

    @Test
    void constructorTest() {
        assertDoesNotThrow(() -> new Study(defaultAmountSubjects));
        assertThrows(IllegalArgumentException.class, () -> new Study(0));
        assertThrows(IllegalArgumentException.class, () -> new Study(-1));
    }

    @Test
    void getAmountSubjectsTest() {
        assertEquals(defaultAmountSubjects, study.getAmountSubjects());
    }

    @Test
    void addCategoryTest() {
        assertDoesNotThrow(() -> study.add(firstCategoryKey, firstCategory));
        assertThrows(NullPointerException.class, () -> study.add(null, firstCategory));
        assertThrows(NullPointerException.class, () -> study.add(firstCategoryKey, null));

        assertEquals(1, study.getCategories().size());
        assertTrue(study.getCategories().containsKey(firstCategoryKey));
        assertTrue(study.getCategories().containsValue(firstCategory));
    }

    @Test
    void getCategoriesTest() {
        study.add(firstCategoryKey, firstCategory);
        Map<String, Category<?>> categoryMap = study.getCategories();

        assertEquals(1, categoryMap.size());
        assertTrue(categoryMap.containsKey(firstCategoryKey));
        assertTrue(categoryMap.containsValue(firstCategory));

        assertThrows(UnsupportedOperationException.class, () -> categoryMap.remove(firstCategoryKey));
        var entrySet = categoryMap.entrySet();
        assertThrows(UnsupportedOperationException.class, () -> entrySet.remove(firstCategory));
    }

    @Test
    void setMissingTest() {
        assertDoesNotThrow(() -> study.setMissing());
        study.add(firstCategoryKey, firstCategory);
        study.setMissing();
        verify(firstCategory).setMissing();
    }

}
