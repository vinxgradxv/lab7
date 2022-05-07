import data.StudyGroup;
import utils.CollectionManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Hashtable;

public class StudyGroupCollection implements CollectionManager {

    private Hashtable<Long, StudyGroup> studyGroupHashtable = new Hashtable<>();
    private final LocalDateTime initializationDate;
    private HashSet<Long> usedId;
    private ParserCSV parserCSV;
    private long curId;

    public StudyGroupCollection(ParserCSV parserCSV, File file) throws FileNotFoundException {
        this.parserCSV = parserCSV;
        initializationDate = LocalDateTime.now();
        curId = 1;
        usedId = new HashSet<>();
        studyGroupHashtable = parserCSV.getCollectionFromFile(file, this);
    }
    @Override
    public boolean clean() {
        if (studyGroupHashtable.isEmpty()){
            return false;
        }
        studyGroupHashtable.clear();
        return true;
    }

    @Override
    public Hashtable<Long, StudyGroup> getStudyGroupHashTable() {
        return studyGroupHashtable;
    }

    @Override
    public LocalDateTime getInitializationDate() {
        return initializationDate;
    }

    @Override
    public int getSize() {
        return studyGroupHashtable.size();
    }

    @Override
    public Class getType() {
        return studyGroupHashtable.getClass();
    }

    @Override
    public boolean add(Long key, StudyGroup studyGroup) {
        if (studyGroupHashtable.containsKey(key)){
            studyGroupHashtable.put(key, studyGroup);
            return false;
        }
        studyGroupHashtable.put(key, studyGroup);
        return true;
    }

    @Override
    public boolean remove(Long key) {
        if (!studyGroupHashtable.containsKey(key)){
            return false;
        }
        studyGroupHashtable.remove(key);
        return true;
    }
    public void setFileFromCollection() throws FileNotFoundException {
        parserCSV.setFileFromCollection(studyGroupHashtable);
    }
    public void addIdToUsed(Long id){
        usedId.add(id);
    }
    public Long getMinFreeId(){
        while (usedId.contains(curId)){
            curId += 1;
        }
        return curId;
    }
    public boolean isIdUsed(Long id){
        return usedId.contains(id);
    }
    public void removeIdFromUSed(Long id){
        usedId.remove(id);
    }
}
