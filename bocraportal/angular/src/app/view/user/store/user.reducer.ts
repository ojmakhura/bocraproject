// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { createFeature, createReducer, on } from '@ngrx/store';
import { UserVO } from '@app/model/bw/org/bocra/portal/user/user-vo';
import * as UserActions from './user.action';
import { UserCriteria } from '@app/model/bw/org/bocra/portal/user/user-criteria';

export const userKey = "user";

export interface UserState {
    user: UserVO;
    users: UserVO[];
    searchCriteria: UserCriteria
    loading: boolean;
    id: string;
    error: any
}

export const initialState: UserState = {
    user: new UserVO,
    users: [],
    searchCriteria: new UserCriteria,
    loading: false,
    id: '',
    error: null
};

export const userReducer = createReducer(
    initialState,
    on(UserActions.saveUser, (state, action) => ({
        ...state,
        user: action.user
    })),
    on(UserActions.saveUserSuccess, (state, action) => ({
        ...state,
        user: action.user,
        users: [...state.users, action.user]
    })),
    on(UserActions.findById, (state, action) => ({
        ...state,
        id: action.id
    })),
    on(UserActions.findByIdSuccess, (state, action) => ({
        ...state,
        user: action.user,
        users: [...state.users, action.user]
    })),
    on(UserActions.loadAll, (state, action) => ({
        ...state
    })),
    on(UserActions.loadAllSuccess, (state, action) => ({
        ...state,
        user: new UserVO,
        userArray: action.users
    })),
    on(UserActions.searchUsers, (state, action) => ({
        ...state,
        searchCriteria: action.searchCriteria
    })),
    on(UserActions.searchUsersSuccess, (state, action) => ({
        ...state,
        user: new UserVO,
        searchCriteria: new UserCriteria,
        userArray: action.users
    })),
    on(UserActions.userActionFailure, (state, action) => ({
        ...state,
        error: action.error
    }))
);

export const userFeature = createFeature(
    {
        name: userKey,
        reducer: userReducer
    }
);
