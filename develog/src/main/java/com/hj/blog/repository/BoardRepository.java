package com.hj.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hj.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{ 

}