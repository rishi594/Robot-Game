import java.util.ArrayList;
import java.util.Arrays;

class RobotControl
 {
   private Robot r;
   
   public RobotControl(Robot r)
   {
       this.r = r;
   }

   public void control(int barHeights[], int blockHeights[], int required[], boolean ordered)
   {

	 // partA();
	 // partB(barHeights);
	 //partC(blockHeights,barHeights);
	partD(blockHeights,barHeights,required);
	   // The first past can be solved easily with out any arrays as the height of bars and blocks are fixed.
	   // Use the method r.up(), r.down(), r.extend(), r.contract(), r.raise(), r.lower(), r.pick(), r.drop()
	   // The code below will cause first arm to be moved up, the second arm to the right and the third to be lowered. 
	   
	  
	   
	   // Part B requires you to access the array barHeights passed as argument as the robot arm must move
	   // over the bars
	   
	     
	   
	   
	   // The third part requires you to access the arrays barHeights and blockHeights 
	   // as the heights of bars and blocks are allowed to vary through command line arguments
	   

	   
	   
	   // The fourth part allows the user  to specify the order in which bars must 
	   // be placed in the target column. This will require you to use the use additional column
	   // which can hold temporary values
	   

	   
	   
	   
	   // The last part requires you to write the code to move from source column to target column using
	   // an additional temporary column but without placing a larger block on top of a smaller block 
	   
   } 
   public void partA()
   {
	   int h=2,w=1,d=0;
	   int source_count=8; // sum of block lengths in source
	   int target_count=0; // sum of block lengths in target
	   while(h<=9)
	   {
		   r.up();
		   h++;
	   }
	   
	    int i=0;
	   while(i<4) // main loop running for 4 times i.e for 4 blocks
	  {
		   while(w<=9)// extending arm to reach top of the blocks in source
		   {
			   r.extend();
			   w++;
		   }
	   
	   while(h>(source_count+1+d))//calculating how much to lower the third arm while picking up blocks from source.
	   {
		   r.lower();
		   d++;
	   }
	  
	   
	   r.pick();
	   
	   while(d>0)
	   {
		   r.raise();
		   d--;
	   }
	   
	   source_count-=2; //updating source and target after blocks have been moved.
	   target_count+=2;
	   
	   while(w>1)
	   {
		   r.contract();
		   w--;
	   }
	  
	   while(h-(target_count+1+d)>0)//calculating how much to lower the third arm while dropping the block on target.
	   {
		   r.lower();
		  
		   d++;
	   }
	   r.drop();
	   while(d>0)
	   {
		   r.raise();
		   d--;
	   }
	   i++;
	  
	  }
   }
 
 public void partB(int barHeights[])
 {
	 int h=2,w=1,d=0,max_barheight=0,i=0;
	 int source_count=8;
	 int target_count=0;
	 int safe_height=0;
	 
	 Arrays.sort(barHeights);// sorting the obstacle lengths to find out max bar height.
	 max_barheight=barHeights[barHeights.length-1]; //assigning max bar height to max_barheight
	 
	
	safe_height=2+max_barheight+1;//calculating the safe height to avoid hitting obstacle i.e where -block height(2) arm(1)
	if(safe_height>8){
		while(h<=safe_height)
		{
			r.up();
			h++;
		}
	   
	}
	else
	{
		while(h<=8){
			r.up();
			h++;
		}
		
	}	 
	 
		 
	  while(i<4) // main loop running for 4 times
	  {
		   while(w<=9)
		   {
			   r.extend();
			   w++;
		   }
	   
	   while(h>(source_count+1+d))
	   {
		   r.lower();//calculating how much to lower the third arm while picking up blocks from source.
		   d++;
	   }
	  
	   
	   r.pick();
	   
	   while(d>0)
	   {
		   r.raise();
		   d--;
	   }
	   
	   source_count-=2; //updating source and target after blocks have been moved.
	   target_count+=2;
	   
	   while(w>1)
	   {
		   r.contract();
		   w--;
	   }
	  
	   while(h-(target_count+1+d)>0)//calculating how much to lower the third arm while dropping the block on target.
	   {
		   r.lower();
		  
		   d++;
	   }
	   r.drop();
	   while(d>0)
	   {
		   r.raise();
		   d--;
	   }
	   i++;
	  
	  }
	 
 }
 

 
 public void partC(int blockHeights[],int barHeights[])
 {
	 int h=2,w=1,d=0,max_barheight=0,i=0;
	 int source_count=0;
	 int target_count=0;
	 int safe_height=0;
	 int max_block=0;
	 int[] temp= new int [blockHeights.length];
	 
	 for( int j=0;j<blockHeights.length-1;j++)// storing block heights in temp array in order to avoid changing original array.
		 temp[j]=blockHeights[j];
	 
	 for(int j=0;j<blockHeights.length;j++)// calculating source_count
		 source_count+=blockHeights[j];
	 
	 Arrays.sort(blockHeights);
	 Arrays.sort(barHeights);// sorting the obstacle lengths to find out max bar height.
	 max_barheight=barHeights[barHeights.length-1];// storing max obstacle length in max_barheight
	 max_block=blockHeights[blockHeights.length-1];// storing max block height in max_block
	
	safe_height=max_block+max_barheight; //calculating safe height to avoid obstacles
	if(safe_height>source_count)
	{
		while(h<=safe_height)
		{
			r.up();                 
			h++;
		}
	}	
		                   
	else                         //choosing appropriate safe height
		while(h<=source_count)
		{
			r.up();
			h++;
		}
		
	
		 
	 while(i<4) // main loop running for 4 times
	  {
		   while(w<=9)
		   {
			   r.extend();
			   w++;
		   }
	  
	   while(h>(source_count+1+d))
	   {
		   r.lower();//calculating how much to lower the third arm while picking up blocks from source.
		   d++;
	   }
	  
	   
	   r.pick();
	   
	   while(d>0)
	   {
		   r.raise();
		   d--;
	   }
	   
	   source_count-=blockHeights[blockHeights.length-i-1]; //updating source and target after blocks moved
	   target_count+=blockHeights[blockHeights.length-i-1];
	   
	   while(w>1)
	   {
		   r.contract();
		   w--;
	   }
	
	   while(h-(target_count+1+d)>0) //calculating how much to lower the third arm while dropping the block on target.
	   {
		   r.lower();
		  
		   d++;
	   }
	   r.drop();
	   while(d>0)
	   {
		   r.raise();
		   d--;
	   }
	   i++;
	  
	  }
 }
 
 public void partD(int blockHeights[], int barHeights[], int required[])
 {
		int h=2,d=0,w=1,v=0, source_count=0,target_count=0, temp_stack=0,max_block=0,max_barheight=0;
		int req_index=0;
		int a=source_count;
		int[] temp_block = new int[blockHeights.length];
		
		for(int i=0;i<blockHeights.length;i++)
			temp_block[i]=blockHeights[i]; //storing block heights in a temp array.
		
		Arrays.sort(barHeights);
		Arrays.sort(temp_block);
		ArrayList<Integer> block_length = new ArrayList<Integer>();
		ArrayList<Integer> required_length = new ArrayList<Integer>();
		ArrayList<Integer> temp_blocklength = new ArrayList<Integer>();
		 
		for (int i = 0; i < blockHeights.length; i++)
		{
			source_count+=blockHeights[i]; // calculating source_count
			block_length.add(blockHeights[i]);//populating given array list
			required_length.add(required[i]);//populating required array list
		}
		
		 
		 boolean target=false; //target is not full
		  max_block=temp_block[blockHeights.length-1]; //assigning max block length
		  max_barheight=barHeights[barHeights.length-1];// assigning max obstacle length
		 
		 int safe_height = max_block + max_barheight;
		 if (safe_height>source_count)
		 {// calculating safe height and choosing appropriate safe height w.r.t inputs.
			 while(h<=safe_height)
			 {
				 r.up();
				 h++;
			 }
		 }	 
		 
		 else
			 while(h<=source_count)
			 {
				 r.up(); 
				 h++;
			 }
				 
		 
		 while(!target)
		 {   //loop will go on until target is not full.

		 
			 if(block_length.size()>0&&block_length.get(block_length.size()-1)==required_length.get(req_index)){//first case, where topmost block in source matches required element in target.
				 
				 
				 while(w<10)
				 {
					 r.extend(); //extend to source
					 w++;
				 }
			
				 while(h>d+1+source_count) //calculating how much to lower the arm in order to pick it
				 {
				 r.lower();
				 d++;
				 }
				 
			 
				 r.pick();
				 v=block_length.size()-1;
			 
				 source_count-=block_length.get(v);//decrement source_count
				 target_count+=block_length.get(v);//increment target_count
			 
				 block_length.remove(v);
			 
				 while(d>0)
				 {
					 r.raise();
					 d--;
				 }
			 
				
				 
				 while(w>1)
				 {
					 r.contract();
					 w--;
				 }
				 
				 while(h>target_count+d+1) //dropping off block to target.
				 {
					 r.lower();
					 d++;
				 }
			 
				
			 
				 r.drop();
			 
				 while(d>0)
				 {
					 r.raise();
					 d--;
				 }
				 
			 
				 if(target_count==a) // this means if target is full (i.e boolean changes its value)  stop executing loop.
					 target=true;
			 
				 req_index++; //incrementing index for required_length 
			 }
			 
			 else if(temp_blocklength.size()>0&&required_length.get(req_index)==temp_blocklength.get(temp_blocklength.size()-1)){
				 //  second case, to check if temp is not empty and topmost block in temp matches required element in target.
				 while(w<9) //extend only till you reach temp.
				 {
					 r.extend();
					 w++;
				 }
				 
				 while(h>d+1+temp_stack) // calculating how much to lower the arm in order to pick up the block
				 {
					 r.lower();
					 d++;
				 }
				 
				 
				 r.pick();
				 
				 while(d>0)
				 {
					 r.raise();
					 d--;
				 }
				 
				 while(w>1)
				 {
					 r.contract();
					 w--;
				 }
				 
				 
				v=temp_blocklength.size()-1;
				 temp_stack-=temp_blocklength.get(v); //decrement temp
				 target_count+=temp_blocklength.get(v);//increment target
				 
				 temp_blocklength.remove(v);//remove top element from temp once it has been shifted to target.
				 while(h>d+1+target_count) //calculating how much to lower arm in order to drop off block at target.
				 {
					 r.lower();
					 d++;
				 }
				
				 
				 r.drop();
				 while(d>0)
				 {
					 r.raise();
					 d--;
				 }
				 
				 
				 req_index++;  //incrementing index for required_length 
				 
				 if(target_count==a) // this means if target is full stop executing loop.
					 target=true;
				 
			 }
			 
			 else if(temp_blocklength.size()>1&&required_length.get(req_index)==temp_blocklength.get(temp_blocklength.size()-2)||temp_blocklength.size()>2&&required_length.get(req_index)==temp_blocklength.get(temp_blocklength.size()-3)){
			//checking in temp whether first indexed element is equal to the required element.(considering possiblities of 2 or 3 elements in temp)	
				 while(w<9)
				 {
					 r.extend();
					 w++;
				 }
				 
				 
				 while(temp_blocklength.size()>=1&&required_length.get(req_index)!=temp_blocklength.get(temp_blocklength.size()-1)){
					 // condition to check if topmost block in temp is not equal to required element in order to move it back to source
					 while(h>d+1+temp_stack)
					 {
						 r.lower();
						 d++;
					 }
					 
					 
					 r.pick();
					 while(d>0)
					 {
						 r.raise();
						 d--;
					 }
					 
					 v=temp_blocklength.size()-1;
					 temp_stack-=temp_blocklength.get(v); //decrementing temp_stack
					 source_count+=temp_blocklength.get(v);//adding the above element to source.
					 block_length.add(temp_blocklength.get(v));//adding in block_length array list
					 temp_blocklength.remove(temp_blocklength.get(v));//removing element from array list
					 while(w<10){
						 r.extend();
						 w++;
					 }
					 
					 while(h>source_count+1+d){
						 r.lower();
						 d++;
					 }
					
					 
					 r.drop();
					 while(d>0){
						 r.raise();
						 d--;
					 }
					 
					 
					 r.contract();
					 w--;
					 
					 if(required_length.get(required_length.size()-1)==temp_blocklength.get(temp_blocklength.size()-1))//to check whether topmost element in temp matches required element.
					 {
						 while(h>d+1+temp_stack)
						 {
							 r.lower();
							 d++;
						 }
						 
						 
						 r.pick();
						 while(d>0)
						 {
							 r.raise();
							 d--;
						 }
						 
						 while(w>1)
						 {
							 r.contract();
							 w--;
						 }
						 
						 v=temp_blocklength.size()-1;
						 temp_stack-=temp_blocklength.get(v); //decrement from temp
						 target_count+=temp_blocklength.get(v);//increment to target
						 temp_blocklength.remove(v);//remove element from temp
						 while(h>d+1+target_count){
							 r.lower();
							 d++;
						 }
						 
						 
						 r.drop();
						 while(d>0)
						 {
							 r.raise();
							 d--;
						 }
						
						 req_index++;
					 }		 
				 }
			 }
			  
			 else {// last condition  where topmost temp element or topmost source element doesnt match the required.(move source element to temp)
				 while(block_length.size()!=0&&required_length.get(req_index)!=block_length.get(block_length.size()-1)){
				 while(w<10)
				 {
					 r.extend();
					 w++;
				 }
				
				 while(h>d+1+source_count)
				 {
					 r.lower();
					 d++;
				 }
				 
				 
				 r.pick();
				 v=block_length.size()-1;
				 source_count-=block_length.get(v); //decrement source count
				 temp_stack+=block_length.get(v);// increment temp 
				 
				 temp_blocklength.add(block_length.get(v)); //adding topmost element of blocklength to temp.
				 block_length.remove(v); //removing the above added element.
				 while(d>0)
				 {
					 r.raise();
					 d--;
				 }
				
				 while(w>9) //contracting arm only till temp
				 {
					 r.contract();
					 w--;
				 }
			
				 while(h>temp_stack+d+1)
				 {
					 r.lower();
					 d++;
				 }
				
				 
				 r.drop();
				 while(d>0)
				 {
					 r.raise();
					 d--;
				 }
				
			 }
		 } 
	 }
	 
 }
 	 
 }


		 
		   