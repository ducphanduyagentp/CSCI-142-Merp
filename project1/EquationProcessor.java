package Processors;

import Util.SymbolTable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ptnega on 10/03/2017.
 * Class to process Merp Programming Language Statements
 */
public class EquationProcessor {

    private MerpProcessor processor;
    private SymbolTable symbolTable;
    private ArrayList<String> equations;

    /**
     * Constructor to create an Equation Processor.
     * @param equations ArrayList containing the equations
     * @param processor the Merp processor to use to process expressions
     */
    public EquationProcessor(ArrayList<String> equations, MerpProcessor processor) {
        this.equations = equations;
        this.processor = processor;
        this.symbolTable = new SymbolTable();
    }

    /**
     * Processes the provided list of statements using the provided Merp Processor and Sysmbol Table.
     */
    public void processEquations() {
        for (String eq: equations) {
            processEquation(eq);
        }
    }

    /**
     * Helper function to process String of equations
     * @param eq the String equation to process
     */
    private void processEquation(String eq) {
        eq = eq.trim();

        if (eq.contains("while")) {
            String dummy = eq.substring(eq.indexOf("while(") + 6, eq.length() - 1);
            String[] statement1 = dummy.trim().split(",");

            String[] statements = statement1[1].trim().split(";");
            ArrayList<String> tokens = new ArrayList<>( Arrays.asList(statement1[0].trim().split(" ")) );
            for (String s: tokens) {
                if (MerpProcessor.isVariable(s)) {
                    this.symbolTable.exists(s);
                }
            }

            this.processor.constructTree(tokens);
            boolean condition = this.processor.evaluateTree(symbolTable) != 0;

            while(condition) {
                for (String s: statements) {
                    processEquation(s);
                }
                this.processor.constructTree(tokens);
                condition = this.processor.evaluateTree(symbolTable) != 0;
            }
        } else if (eq.contains("if")) {
            String dummy = eq.substring(eq.indexOf("if(") + 3, eq.length() - 1);
            String[] statements = dummy.trim().split(",");

            ArrayList<String> tokens = new ArrayList<>( Arrays.asList(statements[0].trim().split(" ")) );
            for (String s: tokens) {
                if (MerpProcessor.isVariable(s)) {
                    this.symbolTable.exists(s);
                }
            }
            
            this.processor.constructTree(tokens);

            boolean condition = this.processor.evaluateTree(symbolTable) != 0;
            if (condition) {
                processEquation(statements[1]);
            } else {
                processEquation(statements[2]);
            }
        } else {
            if (eq.equals("printVars()") || eq.equals("printVariables()")) {
                this.symbolTable.dump();
            } else if (eq.contains("print(")) {
                ArrayList<String> tokens = new ArrayList<>( Arrays.asList(eq.substring(6, eq.length() - 1).trim().split(" ")) );
                for (String s: tokens) {
                    if (MerpProcessor.isVariable(s)) {
                        this.symbolTable.exists(s);
                    }
                }

                this.processor.constructTree(tokens);
                System.out.println(this.processor.evaluateTree(symbolTable));
            } else if (eq.contains(" = ")) {
                String[] exp = eq.trim().split(" = ");

                ArrayList<String> tokens = new ArrayList<>( Arrays.asList(exp[1].trim().split(" ")) );
                for (String s: tokens) {
                    if (MerpProcessor.isVariable(s)) {
                        this.symbolTable.exists(s);
                    }
                }

                this.processor.constructTree(tokens);
                this.symbolTable.put(exp[0], this.processor.evaluateTree(symbolTable));
            } else {
                ArrayList<String> tokens = new ArrayList<>( Arrays.asList(eq.trim().split(" ")) );
                for (String s: tokens) {
                    if (MerpProcessor.isVariable(s)) {
                        this.symbolTable.exists(s);
                    }
                }

                this.processor.constructTree(tokens);
                this.processor.evaluateTree(symbolTable);
            }
        }
    }
}
