with text_io;
with MatrixMult;

procedure AssignmentMain is
    use text_io;
    use MatrixMult;
    package Int_Io is new Integer_Io(Integer);
    use Int_Io;
     
    MatrixA: TwoMatrix;
    MatrixB: TwoMatrix;
    MatrixC: TwoMatrix;
    
    Task type Reader_1 is
        entry Start;
    end Reader_1;
    Task body Reader_1 is
        begin
            accept Start do
            for i in 1 .. SIZE loop
                for j in 1..SIZE loop
                    Get(MatrixA(i,j));
                end loop;
            end loop; 
            end Start;
    end Reader_1;
    
    
    Task type Reader_2 is
        entry Start;
    end Reader_2;
    Task body Reader_2 is
        begin 
            accept Start do 
            
            for i in 1 .. SIZE loop
                for j in 1..SIZE loop
                    Get(MatrixB(i,j));
                end loop;
            end loop; 
            end Start;
    end Reader_2;
    
    Task type Printer is 
        entry Start;
    end Printer;
    Task body Printer is
        begin 
            accept Start do
            for i in 1 .. SIZE loop
                for j in 1..SIZE loop
                    Put(MatrixC(i,j));
                end loop;
                New_Line;
            end loop; 
            end Start;
    end Printer;
    
    T1: Reader_1;
    T2: Reader_2;
    T3: Printer;
    
    begin 
    T1.Start;
    T2.Start; 
    MatMult(MatrixA,MatrixB,MatrixC); 
    T3.Start;
    
end AssignmentMain;