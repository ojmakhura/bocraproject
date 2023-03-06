// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::submission::note::NoteService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.submission.note;

import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.BocraportalSpecifications;

/**
 * @see bw.org.bocra.portal.form.submission.note.NoteService
 */
@Service("noteService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class NoteServiceImpl
    extends NoteServiceBase
{
    public NoteServiceImpl(
        NoteDao note,
        NoteRepository noteRepository,
        MessageSource messageSource
    ) {
        
        super(
            note,
            noteRepository,
            messageSource
        );
    }

    /**
     * @see bw.org.bocra.portal.form.submission.note.NoteService#findById(Long)
     */
    @Override
    protected NoteVO handleFindById(Long id)
        throws Exception
    {
        return noteDao.toNoteVO(noteRepository.getReferenceById(id));
    }

    /**
     * @see bw.org.bocra.portal.form.submission.note.NoteService#save(NoteVO)
     */
    @Override
    protected NoteVO handleSave(NoteVO note)
        throws Exception
    {
        return noteDao.toNoteVO(noteRepository.save(noteDao.noteVOToEntity(note)));
    }

    /**
     * @see bw.org.bocra.portal.form.submission.note.NoteService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        noteRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.form.submission.note.NoteService#getFormSubmissionNotes(Long)
     */
    @Override
    protected Collection<NoteVO> handleGetFormSubmissionNotes(Long formSubmissionId)
        throws Exception
    {
        Specification<Note> spec = BocraportalSpecifications.findByJoinAttribute("formSubmission", "id", formSubmissionId);
        Collection<Note> notes = noteRepository.findAll(spec);

        return noteDao.toNoteVOCollection(notes);
    }

}