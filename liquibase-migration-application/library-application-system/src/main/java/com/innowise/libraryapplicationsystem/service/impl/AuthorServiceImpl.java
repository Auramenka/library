package com.innowise.libraryapplicationsystem.service.impl;

import com.innowise.libraryapplicationsystem.dto.AuthorDto;
import com.innowise.libraryapplicationsystem.exceptions.NotExistsException;
import com.innowise.libraryapplicationsystem.mappers.AuthorMapper;
import com.innowise.libraryapplicationsystem.model.Author;
import com.innowise.libraryapplicationsystem.repository.AuthorRepository;
import com.innowise.libraryapplicationsystem.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_NOT_EXIST = "Author doesn't exist";
    private static final String AUTHOR_IS_EMPTY = "AuthorDto is empty";

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        checkAuthorDto(authorDto);
        return authorMapper.toDto(
                authorRepository.save(authorMapper.toEntity(authorDto)));
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAuthor(Long id) {
        Author authorFromDB = getAuthorFromDB(id);
        authorRepository.deleteById(authorFromDB.getId());
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto authorDto) {
        checkAuthorDto(authorDto);
        Author authorFromDB = getAuthorFromDB(authorDto.getId());
        authorFromDB.setName(authorDto.getName());
        return authorMapper.toDto(authorRepository.save(authorFromDB));
    }

    @Override
    public AuthorDto findById(Long id) {
        return authorMapper.toDto(getAuthorFromDB(id));
    }

    private void checkAuthorDto(AuthorDto authorDto) {
        if (authorDto == null) {
            throw new NotExistsException(AUTHOR_IS_EMPTY);
        }
    }

    private Author getAuthorFromDB(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new NotExistsException(AUTHOR_NOT_EXIST));
    }
}
