package utils;

import data.StudyGroup;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Hashtable;

public interface CollectionManager {
    boolean clean();
    Hashtable<Long, StudyGroup> getStudyGroupHashTable();
    LocalDateTime getInitializationDate();
    int getSize();
    Class getType();
    boolean add(Long key, StudyGroup studyGroup);
    boolean remove(Long key);
    void setFileFromCollection() throws FileNotFoundException;
    Long getMinFreeId();
    boolean isIdUsed(Long id);
    void addIdToUsed(Long id);
    void removeIdFromUSed(Long id);
}
