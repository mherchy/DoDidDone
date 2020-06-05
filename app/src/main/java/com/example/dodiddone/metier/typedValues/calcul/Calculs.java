package com.example.dodiddone.metier.typedValues.calcul;

import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Ligne;
import com.example.dodiddone.metier.Regle;
import com.example.dodiddone.metier.typedValues.TypesEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Calculs {

    private static final Map<String, Calcul> calculs = new HashMap<>();

    static {
        calculs.put("NB_MOY", new Calcul("NB_MOY", "Moyenne", TypesEnum.NB, new Calcul.Calculator() {
            @Override
            public String compute(Cahier cahier, Regle regle) {
                Set<Ligne> lignes = cahier.getLignes(regle);
                return LigneMath.moy(lignes).toString();
            }
        }));
        calculs.put("NB_ECTYPE", new Calcul("NB_ECTYPE", "Ecart-type", TypesEnum.NB, new Calcul.Calculator() {
            @Override
            public String compute(Cahier cahier, Regle regle) {
                Set<Ligne> lignes = cahier.getLignes(regle);
                return LigneMath.ectype(lignes).toString();
            }
        }));
        calculs.put("NB_NBOCC", new Calcul("NB_NBOCC", "Les 3 valeurs les plus présentes", TypesEnum.NB, new Calcul.Calculator() {
            @Override
            public String compute(Cahier cahier, Regle regle) {
                Set<Ligne> lignes = cahier.getLignes(regle);
                return LigneMath.top3(lignes);
            }
        }));
        calculs.put("NB_SUM", new Calcul("NB_SUM", "Somme totale", TypesEnum.NB, new Calcul.Calculator() {
            @Override
            public String compute(Cahier cahier, Regle regle) {
                Set<Ligne> lignes = cahier.getLignes(regle);
                return LigneMath.sum(lignes).toString();
            }
        }));
        calculs.put("NB_MIN", new Calcul("NB_MIN", "Minimum", TypesEnum.NB, new Calcul.Calculator() {
            @Override
            public String compute(Cahier cahier, Regle regle) {
                Set<Ligne> lignes = cahier.getLignes(regle);
                return LigneMath.min(lignes).toString();
            }
        }));
        calculs.put("NB_MAX", new Calcul("NB_MAX", "Maximum", TypesEnum.NB, new Calcul.Calculator() {
            @Override
            public String compute(Cahier cahier, Regle regle) {
                Set<Ligne> lignes = cahier.getLignes(regle);
                return LigneMath.max(lignes).toString();
            }
        }));
        calculs.put("BOOL_NBTRUE", new Calcul("BOOL_NBTRUE", "Nombre de Oui", TypesEnum.BOOL, new Calcul.Calculator() {
            @Override
            public String compute(Cahier cahier, Regle regle) {
                Set<Ligne> lignes = cahier.getLignes(regle);
                return LigneMath.nbyes(lignes);
            }
        }));
        calculs.put("BOOL_NBFALSE", new Calcul("BOOL_NBFALSE", "Nombre de Non", TypesEnum.BOOL, new Calcul.Calculator() {
            @Override
            public String compute(Cahier cahier, Regle regle) {
                Set<Ligne> lignes = cahier.getLignes(regle);
                return LigneMath.nbno(lignes);
            }
        }));
    }

    public static Calcul get(String name) {
        return calculs.get(name);
    }

    public static List<Calcul> get(TypesEnum type) {
        ArrayList<Calcul> compatibles = new ArrayList<>();
        for (Calcul calcul : calculs.values()) {
            if(calcul.getRelatedType().getNom().equals(type.getNom())) {
                compatibles.add(calcul);
            }
        }
        return compatibles;
    }

}
