package com.hj.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hj.blog.config.auth.PrincipalDetail;
import com.hj.blog.dto.ReplySaveRequestDto;
import com.hj.blog.model.Board;
import com.hj.blog.model.Reply;
import com.hj.blog.model.User;
import com.hj.blog.repository.BoardRepository;
import com.hj.blog.repository.ReplyRepository;
import com.hj.blog.repository.UserRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {// title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void 글삭제하기(int id, PrincipalDetail principal) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패 : 해당 글이 존재하지 않습니다.");
		});
		if (board.getUser().getId() != principal.getUser().getId()) {
			throw new IllegalStateException("글 삭제 실패 : 해당 글을 삭제할 권한이 없습니다.");
		}
		boardRepository.delete(board);
	}

	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> {
					return new IllegalArgumentException("글 찾기 실패 : 해당 글이 존재하지 않습니다.");
		}); // 영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시에 (트랜젝션이 service가 종료될 때) 더티체킹 -> 자동 업데이트(db flush/commit)
	}
	
	@Transactional
	// DTO의 이점 : 필요한 데이터를 한방에 받을수 있지
	// 영속화가 귀찮을때는 다른 방법도있다.
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		
		User user = userRepository.findById(replySaveRequestDto.getUserId())
				.orElseThrow(() -> {
					return new IllegalArgumentException("댓글 쓰기 실패: 유저 id를 찾을 수 없습니다.");
		}); // 영속화
		
		Board board = boardRepository.findById(replySaveRequestDto.getBoardId())
				.orElseThrow(() -> {
					return new IllegalArgumentException("댓글 쓰기 실패: 게시글 id를 찾을 수 없습니다.");
		}); // 영속화
		
		
//		Reply reply = new Reply();
//		reply.update(user, board, replySaveRequestDto.getContent());
		
		Reply reply = Reply.builder()
				.user(user)
				.board(board)
				.content(replySaveRequestDto.getContent())
				.build();
		
		//더티체킹의 대상이 아님! board는 타입을 맞춰주기위한 select일뿐
		replyRepository.save(reply);
	}
}
