package org.eclipse.jst.jsf.common.sets.internal.provisional;

import java.util.Iterator;
import java.util.Set;

public class AxiomaticSetUtil 
{
    /**
     * Creates the union of firstSet and secondSet in newSet.
     * @param newSet
     * @param firstSet
     * @param secondSet
     */
    public static void union(AxiomaticSet newSet, AxiomaticSet firstSet, AxiomaticSet secondSet)
    {
        newSet.addAll(firstSet);
        newSet.addAll(secondSet);
    }
    
    public static void intersect(AxiomaticSet newSet, AxiomaticSet firstSet, AxiomaticSet secondSet)
    {
        // minor optimization: always iterator through the smaller of the
        // two sets.  This way we iterate through the smallest number
        // of elements
        Iterator it = null;
        Set  testSet = null;
        // if other set smaller, get its iterator
        if (secondSet.size() < firstSet.size())
        {
            it = secondSet.iterator();
            // test set is other set
            testSet = firstSet;
        }
        // first set is smaller or same
        else
        {
            it = firstSet.iterator();
            testSet = secondSet;
        }
        
        while (it.hasNext())
        {
            Object member = it.next();
            if (testSet.contains(member))
            {
                newSet.add(member);   
            }
        }
    }
    
    public static boolean isDisjoint(AxiomaticSet firstSet, AxiomaticSet secondSet)
    {
        return firstSet.intersect(secondSet).isEmpty();
    }

    public static AxiomaticSet subtract(
            AxiomaticSet firstOperand, AxiomaticSet secondOperand) 
    {
        ConcreteAxiomaticSet  relativeComplement = new ConcreteAxiomaticSet();

        // iterate through firstOperand and add each element to the result
        // set that is not in secondOperand
        for (final Iterator it = firstOperand.iterator(); it.hasNext();)
        {
            Object member = it.next();
            if (!secondOperand.contains(member))
            {
                relativeComplement.add(member);   
            }
        }

        return relativeComplement;
    }
}
