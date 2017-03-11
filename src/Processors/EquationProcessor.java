package Processors;

import Util.SymbolTable;

import java.util.ArrayList;

/**
 * Created by ptnega on 10/03/2017.
 * Class to process Merp Programming Language Statements
 */
public class EquationProcessor {

    MerpProcessor processor;
    SymbolTable symbolTable;
    ArrayList<String> equations;

    public EquationProcessor(ArrayList<String> equations, MerpProcessor processor) {
        this.equations = equations;
        this.processor = processor;
        this.symbolTable = new SymbolTable();
    }

    public void processEquations() {

    }

    private void processEquation(String eq) {

    }
}
