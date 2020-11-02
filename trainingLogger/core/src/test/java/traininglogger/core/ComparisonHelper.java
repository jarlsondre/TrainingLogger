package traininglogger.core; 


/**
 * Klasse som inneholder metoder som flere testklasser bruker.
 */
public class ComparisonHelper {
  
  /**
   * Sammenlikner to Set-objekteter. 
   * Resultatet av sammenlikningen er sann (true) hvis og bare hvis de to objektenes attributter (repetitions og weight)
   * har identiske verdier.
   *
   * @param set1 Det ene set-objektet som skal sammenliknes.
   * @param set2 Det andre set-objektet som skal sammenliknes.
   * @return true dersom de to objektene er identiske, false ellers
   */
  public static boolean equalSet(Set set1, Set set2) {
    return set1.getRepetitions() == set2.getRepetitions() && set1.getWeight() == set2.getWeight();
  }

  public static boolean equalExercise(Exercise e1, Exercise e2) {
  if(!e1.getName().equals(e2.getName())){
    return false;
  }
  Iterator<Set> it1 = e1.iterator();
  Iterator<Set> it2 = e2.iterator();
  while(it1.hasNext() && it2.hasNext()){
    if(!ComparisonHelper.equalSet(it1.next(), it2.next())) {
      return false;
    }
  }
  if(it1.hasNext() || it2.hasNext()){
    return false;
  }
  return true;
}



}