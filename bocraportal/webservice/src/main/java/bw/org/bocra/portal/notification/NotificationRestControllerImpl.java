// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.notification;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.postgresql.util.PSQLException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/notification")
@Tag(name = "Notifications", description = "Managing notifications.")
@CrossOrigin()
public class NotificationRestControllerImpl extends NotificationRestControllerBase {

    public NotificationRestControllerImpl(NotificationService notificationService) {
        super(notificationService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try{
            logger.debug("Search notification by Id "+id);
            Optional<NotificationVO> data = Optional.of(this.notificationService.findById(id)); 
            ResponseEntity<?> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Notification with id %ld not found.", id));
            }
    
            return response;
        } catch (Exception e) {
            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Notification with id %d not found.", id));
            } else {
                message = "An unknown error has occured while loading the notification. Please contact the system administrator.";
            }

            logger.error(message);
            return ResponseEntity.badRequest().body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try{

            logger.debug("Loading all notifications");
            return ResponseEntity.ok().body(this.notificationService.getAll());
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try{
            logger.debug("Load all notifications of the specified page number "+pageNumber+" and page size"+pageSize);
            return ResponseEntity.ok().body(this.notificationService.getAll(pageNumber, pageSize));
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try{
            logger.debug("Deletes all notification by Id "+id );
            boolean rm = notificationService.remove(id);
            ResponseEntity<?> response;

            if(rm) {
                response = ResponseEntity.ok().body(rm);
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete the notification with id " + id);
            }

            return response;
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());

            if(e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete notification with id " + id);
            }

            return ResponseEntity.badRequest().body("Unknown error encountered when deleting notification with id " + id);
        }
    }

    @Override
    public ResponseEntity<?> handleSave(NotificationVO notification) {
        try{
            logger.debug("Save notification " + notification);
            Optional<NotificationVO> data = Optional.of(this.notificationService.save(notification));
            ResponseEntity<?> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not save the access point type.");
            }
    
            return response;
        } catch (NotificationServiceException | IllegalArgumentException e) {

            String message = e.getMessage();
            if(e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {

                if(message.contains("'notification'")) {

                    message = "The notification information is missing.";

                } else if(message.contains("'notification.subject'")) {

                    message = "The notification subject is missing.";

                } else if(message.contains("'notification.targetType'")) {

                    message = "The notification target type is missing.";

                } else if(message.contains("'notification.message'")) {

                    message = "The notification message type is missing.";

                } else if(message.contains("'notification.notificationDate'")) {

                    message = "The notification notification date type is missing.";

                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }
                
                return ResponseEntity.badRequest().body(message);

            } else if(e.getCause() instanceof PSQLException) {

                if (e.getCause().getMessage().contains("duplicate key")) {
                    if(e.getCause().getMessage().contains("(code)")) {

                        return ResponseEntity.badRequest().body("This notification with this code has been already created.");

                    } else if(e.getCause().getMessage().contains("(name)")) {

                        return ResponseEntity.badRequest().body("This notification with this name has been already created.");
                    } else {
                        return ResponseEntity.badRequest().body("This notification is conflicting with an existing one.");
                    }
                    
                }  else if (e.getCause().getMessage().contains("null value in column")) {
                    if (e.getCause().getMessage().contains("column \"created_by\"")) {
                        return ResponseEntity.badRequest().body("The created-by value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"created_date\"")) {
                        return ResponseEntity.badRequest().body("The created date value is missing.");
                    }
                }
                
                return ResponseEntity.badRequest().body("An unknown database has occured.");
            }

            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        } catch(Exception e) {

            // e.printStackTrace();
            e.getCause().printStackTrace();
            logger.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(NotificationCriteria criteria) {
        try{
            logger.debug("Search notification by criteria "+criteria);

            return ResponseEntity.ok().body(this.notificationService.search(criteria));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleLoadUserNotifications(String userId) {
        try {
            logger.debug("User user notifications " + userId);
            Optional<?> data = Optional.of(notificationService.loadUserNotifications(userId));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleGetUnreadCount() {
        return ResponseEntity.ok().body(notificationService.getUnreadCount());
    }
}