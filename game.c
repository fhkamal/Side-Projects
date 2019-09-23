/////////////////////////////////////////////////////////////////////////////
// INTEGRITY INSTRUCTIONS

// Explicitly state the level of collaboration on this question
// Examples:
//   * I discussed ideas with classmate(s) [include name(s)]
//   * I worked together with classmate(s) in the lab [include name(s)]
//   * Classmate [include name] helped me debug my code
//   * I consulted website [include url]
//   * None
// A "None" indicates you completed this question entirely by yourself
// (or with assistance from course staff)
/////////////////////////////////////////////////////////////////////////////
// INTEGRITY STATEMENT:
// I received help from the following sources:

// None

// Name: Farhan Haidar Kamal
// login ID: fhkamal
/////////////////////////////////////////////////////////////////////////////

#include <stdbool.h>
#include <stdio.h>
#include "game_core.h"

bool step(const int c, const int r, const int width, const int height) {
  if(mine_at(c, r)){
    map[r*width + c] = 88;
    return true; 
  }
  else{
    int count = 0;
    for(int i = c - 1; (i <= c + 1) && (i < width); ++i){
      for(int j = r - 1; (j <= r + 1) && (j < height); ++j){
        if(mine_at(i, j)){
          ++count; 
        }
      }
    }
    map[r*width + c] = count;
  }
  return false;
}

bool step_adv(const int c, const int r, const int width, const int height) {
  if(mine_at(c, r)){
    map[r*width + c] = 88;
    return true; 
  }
  else{
    int count = 0;
    for(int i = c - 1; (i <= c + 1) && (i < width); ++i){
      for(int j = r - 1; (j <= r + 1) && (j < height); ++j){
        if(mine_at(i, j)){
          ++count; 
        }
      }
    }
    map[r*width + c] = count;
    for(int i = c - 1; (i <= c + 1) && (i < width); ++i){
      for(int j = r - 1; (j <= r + 1) && (j < height); ++j){
        if(i >= 0 && j>= 0 && map[j*width + i] == UNKNOWN){
          step(i, j, width, height);
          if(map[j*width + i] == 0){
           step_adv(i, j, width, height); 
          }
        }
      }
    }
  }
  return false;
}

bool mark(const int c, const int r, int width, int height) {
  if(map[r*width + c] == UNKNOWN){
    map[r*width + c] = MARKED; 
  }
  else if(map[r*width + c] == MARKED){
    map[r*width + c] = UNKNOWN; 
  }
  return all_marked();
}

void print(int width, int height) {
  for(int i = 0; i < height; ++i){
    for(int j = 0; j < width; ++j){
      if(map[i*width + j] == UNKNOWN){
        printf("%c", 95); 
      }
      else if(map[i*width + j] == MARKED){
        printf("%c", 80);
      }
      else if(map[i*width + j] == 88){
        printf("%c", 88); 
      }
      else{
        printf("%d", map[i*width + j]);
      }
      if(j != width - 1){
        printf(" "); 
      }
    }
    printf("\n");
  }
}
