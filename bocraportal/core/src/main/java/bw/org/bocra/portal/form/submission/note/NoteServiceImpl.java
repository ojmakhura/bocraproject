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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        // TODO implement protected  NoteVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.submission.note.NoteService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.submission.note.NoteService#save(NoteVO)
     */
    @Override
    protected NoteVO handleSave(NoteVO note)
        throws Exception
    {
        // TODO implement protected  NoteVO handleSave(NoteVO note)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.submission.note.NoteService.handleSave(NoteVO note) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.submission.note.NoteService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.submission.note.NoteService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.submission.note.NoteService#getFormSubmissionNotes(Long)
     */
    @Override
    protected Collection<NoteVO> handleGetFormSubmissionNotes(Long formSubmissionId)
        throws Exception
    {
        // TODO implement protected  Collection<Note> handleGetFormSubmissionNotes(Long formSubmissionId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.submission.note.NoteService.handleGetFormSubmissionNotes(Long formSubmissionId) Not implemented!");
    }

}